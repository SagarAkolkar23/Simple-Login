package com.example.firebase.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class authview : ViewModel() {

    //Get instance of firebase authorization
    private val auth :FirebaseAuth = FirebaseAuth.getInstance()
    // This variable declares a MutableLiveData
    private val _authstate = MutableLiveData<AuthState>()
    // creates another variable, authState, that can only be read by other parts of your app,
    val authState : LiveData<AuthState> = _authstate

    // This function checks whether the user is logged in or not
    fun checkAuthStatus(){
        if(auth.currentUser == null){
            _authstate.value = AuthState.Unauthenticated
        }
        else{
            _authstate.value =AuthState.Authenticated
        }
    }


    fun login(email : String, pass : String){

        if(email.isEmpty() || pass.isEmpty()){
            _authstate.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authstate.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _authstate.value = AuthState.Authenticated
                }
                else{
                    _authstate.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }


    fun signup(name  :String, email: String, pass: String, confpass : String){
        if(pass != confpass){
            _authstate.value = AuthState.Error("Enter details correctly")
            return
        }
        if(email.isEmpty()){
            _authstate.value = AuthState.Error("Email cant be empty")
            return
        }
        if(pass.isEmpty()){
            _authstate.value = AuthState.Error("Password cant be empty")
            return
        }
        _authstate.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _authstate.value = AuthState.Authenticated
                }
                else{
                    _authstate.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }

    }


    fun signout(){
        auth.signOut()
        _authstate.value = AuthState.Unauthenticated
    }


}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()
}