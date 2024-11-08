package com.example.chatsimple_firebase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AccountViewModel:ViewModel(){
    var state by mutableStateOf(AccountState())
        private set
    fun addUser(){
        git remote set-url origin git@gitlab.com:codetot/blog-v2.git
    }
}

data class AccountState(
    val fullname:String = "",
    val email:String = "",
    val password:String = "",
    val success:Boolean = false,

)