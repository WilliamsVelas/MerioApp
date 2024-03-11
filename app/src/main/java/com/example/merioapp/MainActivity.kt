package com.example.merioapp


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.merioapp.model.Routes
import com.example.merioapp.presentation.navigation.AppNavigation
import com.example.merioapp.ui.theme.*
import com.example.merioapp.ui.theme.MerioAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MerioAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyTopAndBotomAppBar()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAndBotomAppBar() {
    val navigationController = rememberNavController()

    var showDialog by remember { mutableStateOf(false) }

    if (GlobalVar.showDialogConvertion) {
        MinimalDialog(onDismissRequest = { GlobalVar.showDialogConvertion = false })
    }

    if (GlobalVar.amountConvertion == 0.0f) {
        GlobalVar.showDialogConvertion = true
    }

    Log.i("siii", "${GlobalVar.amountConvertion}")

    val context = LocalContext.current.applicationContext

    val currentDestination =
        navigationController.currentBackStackEntryAsState().value?.destination?.route

    val selected = remember {
        mutableStateOf(Icons.Default.Sell)
    }

    when (currentDestination) {
        "homeScreen", "clientScreen", "productScreen" -> Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Principal_Yellow,
                        titleContentColor = Color.Black,
                    ),
                    actions = {
                        Spacer(modifier = Modifier.size(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center,
                            ) {
                                IconButton(onClick = {

                                }, modifier = Modifier.fillMaxSize()) {
                                    Icon(
                                        modifier = Modifier.fillMaxSize(),
                                        imageVector = Icons.Default.AccountCircle,
                                        contentDescription = "Localized description"
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.size(8.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Juan Antonio Fimenez",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = "Admin",
                                    style = TextStyle(fontSize = 12.sp)
                                )
                            }

                            Spacer(modifier = Modifier.weight(0.3f))
                            Card(
                                border = BorderStroke(1.dp, Success_Color),
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(100.dp),
                            ) {
                                Row(
                                    Modifier
                                        .fillMaxSize()
                                        .background(Background_Card),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(onClick = { GlobalVar.showDialogConvertion = true }) {
                                        Icon(
                                            modifier = Modifier.size(20.dp),
                                            imageVector = Icons.Default.Cached,
                                            tint = Success_Color,
                                            contentDescription = ""
                                        )
                                    }
                                    Text(text = "${GlobalVar.amountConvertion}")
                                }
                            }
                        }

                    },
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Background_Navbar
                ) {
                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Sell
                            navigationController.navigate(Routes.HomeScreen.route) {
                                popUpTo(0)
                            }
                        }, modifier = Modifier
                            .weight(1f)
                            .height(if (selected.value == Icons.Default.Sell) 62.dp else 48.dp)
                    ) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                Icons.Default.Sell,
                                contentDescription = "sell icon",
                                modifier = Modifier.size(if (selected.value == Icons.Default.Sell) 36.dp else 26.dp),
                                tint = if (selected.value == Icons.Default.Sell) Color.White else Color.DarkGray
                            )
                            Text(
                                text = "Ventas",
                                color = if (selected.value == Icons.Default.Sell) Principal_Yellow else Color.DarkGray,
                                fontWeight = if (selected.value == Icons.Default.Sell) FontWeight.Bold else FontWeight.Light
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Groups
                            navigationController.navigate(Routes.ClientScreen.route) {
                                popUpTo(0)
                            }
                        }, modifier = Modifier
                            .weight(1f)
                            .height(if (selected.value == Icons.Default.Groups) 62.dp else 48.dp)
                    ) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                Icons.Default.Groups,
                                contentDescription = "client icon",
                                modifier = Modifier.size(if (selected.value == Icons.Default.Groups) 36.dp else 26.dp),
                                tint = if (selected.value == Icons.Default.Groups) Color.White else Color.DarkGray
                            )
                            Text(
                                text = "Clientes",
                                color = if (selected.value == Icons.Default.Groups) Principal_Yellow else Color.DarkGray,
                                fontWeight = if (selected.value == Icons.Default.Groups) FontWeight.Bold else FontWeight.Light
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Inventory
                            navigationController.navigate(Routes.ProductScreen.route) {
                                popUpTo(0)
                            }
                        }, modifier = Modifier
                            .weight(1f)
                            .height(if (selected.value == Icons.Default.Inventory) 62.dp else 48.dp)
                    ) {


                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Icon(
                                Icons.Default.Inventory,
                                contentDescription = "product icon",
                                modifier = Modifier.size(if (selected.value == Icons.Default.Inventory) 36.dp else 26.dp),
                                tint = if (selected.value == Icons.Default.Inventory) Color.White else Color.DarkGray
                            )
                            Text(
                                text = "Productos",
                                color = if (selected.value == Icons.Default.Inventory) Principal_Yellow else Color.DarkGray,
                                fontWeight = if (selected.value == Icons.Default.Inventory) FontWeight.Bold else FontWeight.Light
                            )
                        }


                    }
                }
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    AppNavigation(navigationController)
                }
            },
        )

        else -> AppNavigation(navigationController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinimalDialog(onDismissRequest: () -> Unit) {
    var amountValue by remember { mutableStateOf(GlobalVar.amountConvertion.toString()) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Principal_Yellow),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Por favor coloque la tasa de cambio",
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            
                            Icon(tint = Color.White ,imageVector = Icons.Default.MonetizationOn, contentDescription = "cash")
                        }

                    Card(
                        modifier = Modifier
                            .height(70.dp)
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                    ) {
                        TextField(
                            modifier = Modifier.fillMaxHeight(),
                            label = { Text(text = "Tasa") },
                            value = amountValue,
                            onValueChange = {
                                amountValue = it
                                if (it.isNotEmpty()) {
                                    GlobalVar.amountConvertion = amountValue.toFloat()
                                }
                            },
                            keyboardActions = KeyboardActions(

                                onNext = {
                                    GlobalVar.showDialogConvertion = false
                                }
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Background_Card,
                                focusedIndicatorColor = Success_Color,
                                focusedLabelColor = Color.Black
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MerioAppTheme {
//        MyTopAndBotomAppBar()
    }
}