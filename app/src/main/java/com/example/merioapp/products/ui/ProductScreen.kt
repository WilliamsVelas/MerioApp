package com.example.merioapp.products.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.merioapp.clients.ui.ClientScreen
import com.example.merioapp.ui.theme.MerioAppTheme
import com.example.merioapp.ui.theme.Principal_Yellow

@Composable
fun ProductScreen(navHostController: NavHostController){
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Products", fontSize = 30.sp, color = Principal_Yellow)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MerioAppTheme {
//        ProductScreen()
    }
}
