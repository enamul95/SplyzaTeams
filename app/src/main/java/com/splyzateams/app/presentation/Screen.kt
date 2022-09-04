package com.splyzateams.app.presentation

sealed class Screen(val route: String){
    object HomeScreen: Screen("home_screen")
    object TeamsScreen: Screen("teams_screen")
    object QRScreen: Screen("qr_generate_screen")
}
