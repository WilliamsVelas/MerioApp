package com.example.merioapp.addSell.ui

import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.merioapp.model.Routes
import com.example.merioapp.ui.domain.entity.Client
import com.example.merioapp.ui.domain.entity.Product
import com.example.merioapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateSell(
    navHostController: NavHostController,
    viewModel: AddSellViewModel = hiltViewModel()
) {

    val products = viewModel.products.collectAsState(initial = emptyList())
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    val clients = viewModel.clients.collectAsState(initial = emptyList())
    var selectedClient by remember { mutableStateOf<Client?>(null) }

    var expandedProducts by remember { mutableStateOf(false) }
    var expandedClients by remember { mutableStateOf(false) }


    Scaffold(
        containerColor = Background_Screen,
        topBar = {
            TopAppBar(
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
                title = {
                    Text(text = "Agregar Venta")
                },
            )
        },
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Datos Cliente", style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .height(70.dp)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expandedClients,
                        onExpandedChange = { expandedClients = !expandedClients }) {

                        TextField(
                            modifier = Modifier.menuAnchor(),
                            readOnly = true,
                            placeholder = { Text("Seleccione un Cliente") },
                            value = selectedClient?.name_client ?: "",
                            onValueChange = {},
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedClients) },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Principal_Item,
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = expandedClients,
                            onDismissRequest = { expandedClients = false }) {
                            DropdownMenuItem(
                                text = { Text("Agregar Cliente") },
                                onClick = {
                                    expandedClients = false
                                    navHostController.navigate(Routes.CreateClientScreen.route)
                                })
                            clients.value.forEach { client ->

                                DropdownMenuItem(
                                    text = { Text("${client.name_client}  ${client.identification_client}") },
                                    onClick = {
                                        selectedClient = client
                                        viewModel.client_id = client.id
                                        viewModel.name_client = client.name_client
                                        viewModel.description = client.description
                                        viewModel.identification_client =
                                            client.identification_client
                                        viewModel.client_email = client.email
                                        viewModel.client_phone = client.phone_number
                                        expandedClients = false
                                    })
                            }

                        }
                    }
                }
            }
             
            Spacer(modifier = Modifier.height(8.dp))

            Divider(
                color = Background_Card,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )

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
                    value = viewModel.name_client,
                    singleLine = true,
                    readOnly = true,
                    onValueChange = {
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
                    label = { Text(text = "Cedula/RIF") },
                    value = selectedClient?.identification_client ?: "",
                    singleLine = true,
                    readOnly = true,
                    onValueChange = {
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
                    label = { Text(text = "Descripcion") },
                    value = selectedClient?.description ?: "",
                    onValueChange = {
                    },
                    readOnly = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Background_Card,
                        focusedIndicatorColor = Success_Color,
                        focusedLabelColor = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Datos Producto", style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Card(
                    modifier = Modifier
                        .height(70.dp)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                ) {

                    ExposedDropdownMenuBox(
                        expanded = expandedProducts,
                        onExpandedChange = { expandedProducts = !expandedProducts }) {

                        TextField(
                            modifier = Modifier.menuAnchor(),
                            readOnly = true,
                            placeholder = { Text("Seleccione un Producto") },
                            value = selectedProduct?.name_product ?: "",
                            onValueChange = {},
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedProducts) },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Principal_Item,
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = expandedProducts,
                            onDismissRequest = { expandedProducts = false }) {
                            DropdownMenuItem(
                                text = { Text("Agregar Producto") },
                                onClick = {
                                    expandedProducts = false
                                    navHostController.navigate(Routes.CreateProductScreen.route)
                                })
                            products.value.forEach { product ->

                                DropdownMenuItem(
                                    text = { Text("${product.name_product}") },
                                    onClick = {
                                        selectedProduct = product
                                        viewModel.product_id = product.id
                                        viewModel.name_product = product.name_product
                                        viewModel.provider = product.provider
                                        viewModel.serial_product = product.serial_product
                                        viewModel.price_product =
                                            product.price_product.toString()
                                        expandedProducts = false
                                    })
                            }

                        }
                    }
                }


            }

            Spacer(modifier = Modifier.height(8.dp))



            Divider(
                color = Background_Card,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )

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
                    label = { Text(text = "Nombre") },
                    value = viewModel.name_product,
                    singleLine = true,
                    readOnly = true,
                    onValueChange = {

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
                    readOnly = true,
                    onValueChange = {
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
                    readOnly = true,
                    onValueChange = {
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
                    readOnly = true,
                    onValueChange = {
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Background_Card,
                        focusedIndicatorColor = Success_Color,
                        focusedLabelColor = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Card(
                    modifier = Modifier
                        .width(250.dp)
                        .height(70.dp)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                ) {
                    TextField(
                        modifier = Modifier.fillMaxHeight(),
                        label = { Text(text = "Ganancia " + " : ${viewModel.profitTypeSelected}") },
                        trailingIcon = {
                            IconButton(onClick = { viewModel.expandedMenu = true }) {
                                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                            }
                        },
                        value = viewModel.profit_product,
                        onValueChange = {
                            viewModel.profit_product = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Background_Card,
                            focusedIndicatorColor = Success_Color,
                            focusedLabelColor = Color.Black
                        )
                    )

                    DropdownMenu(
                        expanded = viewModel.expandedMenu,
                        onDismissRequest = { viewModel.expandedMenu = false }
                    ) {
                        viewModel.profitTypeList.forEach { type ->
                            DropdownMenuItem(onClick = {
                                viewModel.profitTypeSelected = type
                                viewModel.expandedMenu = false
                            }, text = { Text(text = type) })
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    modifier = Modifier
                        .padding(2.dp),
                    onClick = {
                    },
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
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    modifier = Modifier
                        .padding(2.dp),
                    onClick = {
                        viewModel.addSell()
                        navHostController.popBackStack()
                    },
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