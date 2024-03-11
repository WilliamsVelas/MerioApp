package com.example.merioapp.addClient.ui

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.merioapp.addSell.ui.AddSellViewModel
import com.example.merioapp.ui.theme.Background_Card
import com.example.merioapp.ui.theme.Background_Screen
import com.example.merioapp.ui.theme.Danger_Color
import com.example.merioapp.ui.theme.Principal_Yellow
import com.example.merioapp.ui.theme.Success_Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateClient(
    navHostController: NavHostController,
    viewModel: AddClientViewModel = hiltViewModel()
) {
    Box(Modifier.fillMaxHeight()) {
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
                        Text(text = "Agregar Cliente")
                    },
                )
            },
        ) {

            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState())
            ) {
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
                        onValueChange = {
                            viewModel.name_client = it
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
                        label = { Text(text = "Cedula/RIF " + ": ${viewModel.selectedPrefix}") },
                        trailingIcon = {
                            IconButton(onClick = { viewModel.expandedMenu = true }) {
                                Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                            }
                        },
                        value = viewModel.identification_client,
                        singleLine = true,
                        onValueChange = {
                            viewModel.identification_client = it
                        },
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
                        viewModel.idenditificationPrefix.forEach { prefix ->
                            DropdownMenuItem(onClick = {
                                viewModel.selectedPrefix = prefix
                                viewModel.expandedMenu = false
                            }, text = { Text(text = prefix) })
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

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
                        label = { Text(text = "Email") },
                        leadingIcon = {
                            Icon(
                                tint = Color.LightGray,
                                imageVector = Icons.Default.Email,
                                contentDescription = "emailIcon"
                            )
                        },
                        value = viewModel.email,
                        singleLine = true,
                        onValueChange = {
                            viewModel.email = it
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        isError = !viewModel.email.contains("@") || !viewModel.email.endsWith(".com"),
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
                        label = { Text(text = "Telefono") },
                        value = viewModel.phone_number,
                        singleLine = true,
                        onValueChange = {
                            viewModel.phone_number = it
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Background_Card,
                            focusedIndicatorColor = Success_Color,
                            focusedLabelColor = Color.Black
                        )
                    )
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
                        label = { Text(text = "Descripcion") },
                        value = viewModel.description,
                        onValueChange = {
                            viewModel.description = it
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Background_Card,
                            focusedIndicatorColor = Success_Color,
                            focusedLabelColor = Color.Black
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Spacer(modifier = Modifier.height(16.dp))

                Row(Modifier.align(Alignment.CenterHorizontally)) {
                    Button(
                        modifier = Modifier
                            .padding(2.dp),
                        onClick = {
                            viewModel.name_client = ""
                            viewModel.email = ""
                            viewModel.phone_number = ""
                            viewModel.description = ""
                            viewModel.identification_client = ""
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
                            viewModel.addClient()
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
}
