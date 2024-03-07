package com.example.merioapp.products.ui

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.PersonOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.merioapp.home.ui.getConvertProfit
import com.example.merioapp.model.Routes
import com.example.merioapp.ui.theme.MerioAppTheme
import com.example.merioapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navHostController: NavHostController,
    viewModel: ProductScreenViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val products = viewModel.products.collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Principal_Yellow)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 26.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .width(200.dp)
                        .height(90.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Background_Card,
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp),
                        shape = RoundedCornerShape(0.dp),
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
                            text = "Productos",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${products.value.size}",
                            style = TextStyle(
                                color = Secondary_Yellow,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )


                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd

            ) {
                Card(
                    Modifier
                        .height(710.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Background_Card,
                    ),
                    shape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp),
                ) {
                    if (products.value.isEmpty()) {
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
                                    text = "No hay Products que mostrar", style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        ) {
                            items(products.value) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp)
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
                                        Spacer(modifier = Modifier.height(16.dp))

                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Text(
                                                text = it.name_product, style = TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = "Proveedor:  ${it.provider}", style = TextStyle(
                                                    fontSize = 14.sp,
                                                )
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = "Precio:  ${it.price_product}", style = TextStyle(
                                                    fontSize = 14.sp,
                                                )
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = "Serial:  ${it.serial_product}", style = TextStyle(
                                                    fontSize = 14.sp,
                                                )
                                            )
                                        }

                                        Spacer(modifier = Modifier.weight(1f))

                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Icon(
                                                modifier = Modifier.size(80.dp),
                                                tint = Color.LightGray,
                                                imageVector = Icons.Default.Image,
                                                contentDescription = "imageicon"
                                            )
                                            Icon(
                                                modifier = Modifier.size(24.dp),
                                                tint = Danger_Color,
                                                imageVector = Icons.Outlined.Delete,
                                                contentDescription = "trash"
                                            )
                                        }
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
                            navHostController.navigate(Routes.CreateProductScreen.route)
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Success_Color,
                            disabledContentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Agregar", style = TextStyle(
                                fontWeight = FontWeight.Bold
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
    }
}
