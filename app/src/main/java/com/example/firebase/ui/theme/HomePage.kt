package com.example.firebase.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun h(navController: NavController, authview : authview){

    val AuthState = authview.authState.observeAsState()
    
    //LaunchedEffect is a composable in jetpack compose allows us to run a block of code whenever certain values change
    // In current condition whenever AuthState changes and if AuthState -> Unauthenticated then navigate to login Screen
    LaunchedEffect(AuthState.value){
        when(AuthState.value){
            is AuthState.Unauthenticated -> navController.navigate("login")

             else -> Unit
        }
    }


    Box(){
        Text(text = "Welcome !!",
            fontSize = 75.sp,
            modifier = Modifier
                .align(alignment = Alignment.Center))
    }

}