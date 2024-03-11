package com.example.merioapp.addProduct.ui

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.merioapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProduct(
    navHostController: NavHostController,
    viewModel: AddProductViewModel = hiltViewModel()
) {
    Scaffold(containerColor = Background_Screen,
        topBar = {
            TopAppBar(
                title = { Text(text = "Agregar Producto") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background_Screen,
                    titleContentColor = Color.Black,
                ),
                actions = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "arrow back"
                        )
                    }
                },
            )
        }
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {

                Row(
                    Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Icon(
                        modifier = Modifier.size(100.dp),
                        imageVector = Icons.Outlined.BrokenImage,
                        tint = Color.LightGray,
                        contentDescription = ""
                    )

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.height(25.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Success_Color,
                            disabledContentColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "Agregar", style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 8.sp
                            )
                        )
                    }
                }

            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
            ) {
                TextField(
                    modifier = Modifier.fillMaxSize(),
                    label = { Text(text = "Nombre") },
                    value = viewModel.name_product,
                    singleLine = true,
                    onValueChange = {
                        viewModel.name_product = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Background_Card,
                        focusedIndicatorColor = Success_Color,
                        focusedLabelColor = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
            ) {
                TextField(
                    modifier = Modifier.fillMaxSize(),
                    label = { Text(text = "Proveedor") },
                    value = viewModel.provider,
                    singleLine = true,
                    onValueChange = {
                        viewModel.provider = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Background_Card,
                        focusedIndicatorColor = Success_Color,
                        focusedLabelColor = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
            ) {
                TextField(
                    modifier = Modifier.fillMaxSize(),
                    label = { Text(text = "Serial") },
                    value = viewModel.serial_product,
                    singleLine = true,
                    onValueChange = {
                        viewModel.serial_product = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Background_Card,
                        focusedIndicatorColor = Success_Color,
                        focusedLabelColor = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
            ) {
                TextField(
                    modifier = Modifier.fillMaxSize(),
                    label = { Text(text = "Precio") },
                    value = viewModel.price_product,
                    singleLine = true,
                    onValueChange = {
                        viewModel.price_product = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Background_Card,
                        focusedIndicatorColor = Success_Color,
                        focusedLabelColor = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    onClick = { viewModel.cleanInputs() },
                    modifier = Modifier
                        .padding(2.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Danger_Color,
                        disabledContentColor = Color.White
                    ),
                ) {
                    Text(
                        text = "Borrar", style = TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Button(
                    onClick = {
                        viewModel.addProduct()
                        navHostController.popBackStack()
                    },
                    modifier = Modifier
                        .padding(2.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Success_Color,
                        disabledContentColor = Color.White
                    ),
                ) {
                    Text(
                        text = "Guardar", style = TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
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
