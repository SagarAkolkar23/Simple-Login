package com.example.firebase.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase.l


@Composable
fun Navigate(modifier : Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login") {
                l(navController, authview = authview())
        }
        composable("signUP") {
            s(navController, authview = authview())
        }
        composable("HomePage") {
            h(navController, authview = authview())
        }
    })
}
