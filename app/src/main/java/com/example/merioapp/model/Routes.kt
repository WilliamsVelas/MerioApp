package com.example.merioapp.model

sealed class Routes(val route: String) {
    //Principals Screens

    data object HomeScreen:Routes("homeScreen")
    data object ClientScreen:Routes("clientScreen")
    data object ProductScreen:Routes("productScreen")

    //Formularies Screens
    data object CreateClientScreen:Routes("createClientScreen")
    data object CreateProductScreen:Routes("createProductScreen")
    data object CreateSellScreen:Routes("createSellScreen")

    //??
    data object ClientProfileScreen:Routes("clientProfileScreen")
    data object MyTopAndBottomBar:Routes("myTopAndBottomBar")
}