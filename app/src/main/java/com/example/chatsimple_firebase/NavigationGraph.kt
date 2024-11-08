package com.example.chatsimple_firebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

sealed class NavRoute (val route:String){
    object WELCOME:NavRoute("welcome_screen")
    object LOGIN:NavRoute("login_screen")
    object REGISTER:NavRoute("register_screen")
    object HOME:NavRoute("contactlist_screen")

}

@Composable
fun NavigatoinGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = if(Firebase.auth.currentUser != null){
            NavRoute.HOME.route
        }
            else{
                NavRoute.WELCOME.route
        }
    ){
        composable(NavRoute.WELCOME.route){
            WelcomeScreen(navController)
        }
        composable(NavRoute.REGISTER.route){
            RegisterScreen(navController)
        }
        composable(NavRoute.LOGIN.route){
            LoginScreen(navController)
        }
        composable(NavRoute.HOME.route){
            ContactListScreen(navController)
        }

    }
}


