package com.example.merioapp.clients.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.PersonOff
import androidx.compose.material.icons.outlined.PersonSearch
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.merioapp.addClient.ui.AddClientViewModel
import com.example.merioapp.addClient.ui.CreateClient
import com.example.merioapp.model.Routes
import com.example.merioapp.ui.theme.*
import com.example.merioapp.ui.theme.MerioAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientScreen(navHostController: NavHostController, viewModel: ClientsScreenViewModel  = hiltViewModel()) {

    var showDialog by remember { mutableStateOf(false) }

    val clients = viewModel.client.collectAsState(initial = emptyList())

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Background_Screen)
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(8.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = Background_Card,
                    contentColor = Color.Black
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Principal_Yellow2,
                        contentColor = Color.Black
                    ),
                ) {

                }
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = Icons.Outlined.PersonSearch,
                        contentDescription = "ClientIcon",
                        modifier = Modifier.size(28.dp),
                    )

                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Clientes Totales", style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "${clients.value.size}", style = TextStyle(
                            color = Success_Color,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(36.dp),
                onClick = {
                    navHostController.navigate(Routes.CreateClientScreen.route)
//                    showDialog = true
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Success_Color,
                    disabledContentColor = Color.White
                ),
            ) {
                Text(text = "Agregar",  style = TextStyle(
                    fontWeight = FontWeight.Bold
                ))
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (clients.value.isEmpty()){
                Box(modifier = Modifier.fillMaxWidth().height(200.dp).align(Alignment.CenterHorizontally) ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.Outlined.PersonOff,
                            contentDescription = "ClientIcon",
                            tint = Danger_Color,
                            modifier = Modifier.size(28.dp),
                        )

                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "No hay Clientes que mostrar", style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(clients.value) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(8.dp),
                            shape = MaterialTheme.shapes.large,
                            colors = CardDefaults.cardColors(
                                containerColor = Principal_Item,
                                contentColor = Color.Black
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 8.dp
                            ),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Spacer(modifier = Modifier.width(16.dp))
                                Icon(
                                    imageVector = Icons.Outlined.AccountCircle,
                                    contentDescription = "Favorite Icon",
                                    modifier = Modifier.size(42.dp),
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = it.name_client, style = TextStyle(
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Text(
                                        text = it.phone_number, style = TextStyle(
                                            fontSize = 12.sp,
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))

                                IconButton(modifier = Modifier
                                    .fillMaxHeight()
                                    .background(Btn_Secondary)
                                    .width(45.dp),
                                    onClick = { navHostController.navigate(Routes.ClientProfileScreen.route) }) {
                                    Icon(
                                        imageVector = Icons.Outlined.ArrowForwardIos,
                                        contentDescription = "ClientIcon",
                                        modifier = Modifier.size(20.dp),
                                    )
                                }

                            }
                        }
                    }
                }
            }


        }
    }

//    if (showDialog) {
//        AlertDialog(
//            onDismissRequest = { showDialog = false },
//            title = { Text("Agregar elemento") },
//            confirmButton = {
//                Button(
//                    onClick = { /* Lógica para agregar elemento */ }
//                ) {
//                    Text("Agregar")
//                }
//            },
//            dismissButton = {
//                Button(
//                    onClick = { showDialog = false }
//                ) {
//                    Text("Cancelar")
//                }
//            },
//            text = {
//                CreateClient()
//            }
//        )
//    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MerioAppTheme {
//        ClientScreen()
    }
}
