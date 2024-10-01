package com.example.firebase

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import com.example.firebase.ui.theme.AuthState
import com.example.firebase.ui.theme.FirebaseTheme
import com.example.firebase.ui.theme.Navigate
import com.example.firebase.ui.theme.authview
import com.example.firebase.ui.theme.s

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authview : authview by viewModels()
        setContent {
            FirebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                        Navigate(modifier = Modifier)

                }
            }
        }
    }
}



@SuppressLint("SuspiciousIndentation")
@Composable
fun l(navController: NavController, authview: authview){


    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val context = LocalContext.current
    val authstate = authview.authState.observeAsState()

        Column(modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Login page",
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                fontSize = 50.sp)
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = email,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                onValueChange = { email = it },
                label = { Text(text = "Email") })

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = pass,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                onValueChange = { pass = it },
                label = { Text(text = "Password") })
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                             authview.login(email, pass)
                 },
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)) {
                Text(text = "Login")
            }
            TextButton(onClick = { navController.navigate("signUP") },
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)) {
                Text(text = "Don't have an account ? Sign up !")
            }
            when (authstate.value) {
                is AuthState.Loading -> {
                    Text(text = "Loading...", modifier = Modifier.padding(16.dp))
                }
                is AuthState.Authenticated -> {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                    navController.navigate("HomePage")
                }
                is AuthState.Error -> {
                    val errorMessage = (authstate as AuthState.Error).message
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // This handles the unauthenticated state or any other case
                }
            }
        }
}


@Composable
@Preview
fun lp(){
    val navController = rememberNavController()

}