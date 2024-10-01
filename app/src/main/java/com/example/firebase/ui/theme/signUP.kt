package com.example.firebase.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun s(navController: NavController, authview: authview){

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var confpass by remember { mutableStateOf("") }

    val authstate = authview.authState.observeAsState()
    val context = LocalContext.current



    LaunchedEffect(authstate.value){
        when(authstate.value){
            is AuthState.Authenticated -> navController.navigate("login")
            is AuthState.Error -> Toast.makeText(context, (authstate.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Sign page",
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
            fontSize = 50.sp)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = name,
            modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally),
            onValueChange = { name = it },
            label = { Text(text = "name") })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = email,modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally),
            onValueChange = { email = it },
            label = { Text(text = "Email") })

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = pass,modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally),
            onValueChange = { pass = it },
            label = { Text(text = "Password") })
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = confpass,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
            onValueChange = { confpass = it },
            label = { Text(text = "Confirm Password") })
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { authview.signup(name, email, pass, confpass)
                         if(authstate.value is AuthState.Authenticated){
                             navController.navigate("Login")
                         }
                         else{

                         }},
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)) {
            Text(text = "Sign up")
        }
        TextButton(onClick = { navController.navigate("login") },
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)) {
            Text(text = "Already hava an account ? Login !")
        }
    }
}

@Composable
@Preview
fun sp(){
    val navController = rememberNavController()

}