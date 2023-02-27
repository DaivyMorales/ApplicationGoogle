package com.example.applicationgoogle

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.applicationgoogle.Screens.LoginScreen
import com.example.applicationgoogle.Screens.MainViewModel
import com.example.applicationgoogle.ui.theme.ApplicationGoogleTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel : MainViewModel by viewModels()

            val isLoading by viewModel.isLoading().observeAsState(false)

            ApplicationGoogleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen( isLoading ) {
                        viewModel.LoginWithGoogle( this@MainActivity )
                    }
                }
            }
        }
    }//Fin del onCreate

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, navController: NavController) {
        super.onActivityResult(requestCode, resultCode, data)

        val viewModel : MainViewModel by viewModels()

        if (requestCode == 1){
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.finishLogin(task, navController)
        }
    }
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