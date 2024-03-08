package com.example.merioapp.home.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.outlined.PersonOff
import androidx.compose.material.icons.outlined.PersonSearch
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.merioapp.GlobalVar
import com.example.merioapp.addSell.ui.CreateSell
import com.example.merioapp.model.Routes
import com.example.merioapp.ui.domain.entity.Sell
import com.example.merioapp.ui.theme.MerioAppTheme
import com.example.merioapp.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val sells = viewModel.sells.collectAsState(initial = emptyList())

    val totalProfit = getTotalPorfit(sells.value)

    val selected = remember {
        mutableStateOf(Icons.Default.Sell)
    }

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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(16.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = Background_Card,
                    contentColor = Color.Black
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
            ) {
                Row(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Card(
                        modifier = Modifier
                            .width(160.dp)
                            .padding(12.dp),
                        shape = MaterialTheme.shapes.large,
                        border = BorderStroke(1.dp, Secondary_Yellow),
                        colors = CardDefaults.cardColors(
                            containerColor = Principal_Item,
                            contentColor = Color.Black
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Secondary_Yellow,
                                contentColor = Color.Black
                            ),
                        ) {

                        }
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "Ventas",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "${sells.value.size}",
                                style = TextStyle(
                                    color = Secondary_Yellow,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )


                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Card(
                        modifier = Modifier
                            .width(160.dp)
                            .padding(12.dp),
                        border = BorderStroke(1.dp, Success_Color),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = Principal_Item,
                            contentColor = Color.Black
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Success_Color,
                                contentColor = Color.Black
                            ),
                        ) {

                        }
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Ganancias",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "${totalProfit}",
                                style = TextStyle(
                                    color = Success_Color,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
            if (sells.value.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
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
                            text = "No hay Ventas que mostrar", style = TextStyle(
                                color = Color.Black,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(sells.value) {
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
                                    imageVector = Icons.Default.CheckCircle,
                                    tint = Success_Color,
                                    contentDescription = "Favorite Icon",
                                    modifier = Modifier.size(28.dp),
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
                                        text = it.provider, style = TextStyle(
                                            fontSize = 12.sp,
                                        )
                                    )
                                }

                                Spacer(modifier = Modifier.weight(1f))

                                Text(
                                    text = "${
                                        if (GlobalVar.isDolar){
                                            getConvertProfit(
                                                it.profit_product,
                                                GlobalVar.amountConvertion
                                            )
                                        }else {
                                            it.profit_product
                                        }
                                    } $", style = TextStyle(
                                        color = Success_Color,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(2.dp),
                onClick = {
                    navHostController.navigate(Routes.CreateSellScreen.route)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Success_Color,
                    disabledContentColor = Color.White
                ),
            ) {
                Text(
                    text = "Nueva venta", style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

fun getTotalPorfit(sell: List<Sell>): Float {
    var totalProfit = 0.0f
    sell.forEach { sell ->
        totalProfit += sell.profit_product
    }
    return totalProfit
}

fun getConvertProfit(value: Float, float: Float): Float {

    var convertedProfit = value * float
    return convertedProfit

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MerioAppTheme {
//        HomeScreen()
    }
}