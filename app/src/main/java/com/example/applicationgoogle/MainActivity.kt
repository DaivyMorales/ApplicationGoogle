package com.example.applicationgoogle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.applicationgoogle.Screens.LoginScreen
import com.example.applicationgoogle.Screens.MainViewModel
import com.example.applicationgoogle.ui.theme.ApplicationGoogleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //Creacion de la variable de estado
            //var isLoading by remember {

                /*{isLoading iniciara en false por default}*/
              //  mutableStateOf(false)
            //}

            val viewModel : MainViewModel by viewModels()

            val isLoading by viewModel.isLoading().observeAsState(false)



            ApplicationGoogleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen( isLoading ) {
                        viewModel.LoginWithGoogle()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApplicationGoogleTheme {
        LoginScreen(false){
            //TODO
        }
    }
}