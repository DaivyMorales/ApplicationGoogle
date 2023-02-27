package com.example.applicationgoogle.navigation

sealed class AppNav( val route : String ){
    object HomeScreen : AppNav(route = "HomeScreen")
    object LoginScreen : AppNav(route = "LoginScreen")
}


