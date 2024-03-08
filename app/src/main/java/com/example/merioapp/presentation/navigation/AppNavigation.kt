package com.example.merioapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.merioapp.addClient.ui.CreateClient
import com.example.merioapp.addProduct.ui.CreateProduct
import com.example.merioapp.addSell.ui.CreateSell
import com.example.merioapp.clientProfile.ui.ClientProfile
import com.example.merioapp.clients.ui.ClientScreen
import com.example.merioapp.home.ui.HomeScreen
import com.example.merioapp.model.Routes
import com.example.merioapp.products.ui.ProductScreen

@Composable
fun AppNavigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Routes.HomeScreen.route) {
        //SELLS
        composable(Routes.HomeScreen.route) { HomeScreen(navHostController) }
        composable(Routes.CreateSellScreen.route) { CreateSell(navHostController) }

        //CLIENT

        composable(Routes.ClientScreen.route) { ClientScreen(navHostController) }
        composable(Routes.CreateClientScreen.route) { CreateClient(navHostController) }
        composable(route = Routes.ClientProfileScreen.route, arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
            }
        )) {
            ClientProfile(navHostController)
        }


        //PRODUCT
        composable(Routes.ProductScreen.route) { ProductScreen(navHostController) }
        composable(Routes.CreateProductScreen.route) { CreateProduct(navHostController) }
    }
}