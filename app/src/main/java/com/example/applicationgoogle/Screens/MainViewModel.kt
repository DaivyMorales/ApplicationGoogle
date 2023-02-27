package com.example.applicationgoogle.Screens

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationgoogle.MainActivity
import com.example.applicationgoogle.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val isLoading = MutableLiveData(false)

    //Evita que la variable sea modificada
    fun isLoading() : LiveData<Boolean> = isLoading

    //Permite cambiar el valor de la variable con el metodo post
    fun LoginWithGoogle(activity: Activity){
        isLoading.postValue(true)

        val gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken( activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val client = GoogleSignIn.getClient(activity,gso)
        val signInIntent : Intent = client.getSignInIntent()
        activity.startActivityForResult(signInIntent, 1)


    }//Fin del LoginWithGoogle

    fun finishLogin( task : Task<GoogleSignInAccount>){
        try {
            val account : GoogleSignInAccount? = task.getResult(ApiException::class.java)
            account?.idToken.let {
                    token ->
                val auth = FirebaseAuth.getInstance()
                val credential = GoogleAuthProvider.getCredential(token, null)
                auth.signInWithCredential(credential).addOnCompleteListener{
                        task ->
                    if (task.isSuccessful){
                        var user = auth.currentUser
                        Log.d(ContentValues.TAG,"Ingreso ${user?.displayName}")
                    }else{
                        Log.d(ContentValues.TAG,"No se ha podido conectar")
                    }
                }


            }
        }catch(e : ApiException){
            Log.d(ContentValues.TAG, "Ha fallado el logeo : code" + e.statusCode)
            isLoading.postValue(false)
        }
    }
}