package com.example.chatsimple_firebase

data class Messsage(
    val sender:String = "",
    val receiver:String = "",
    val content:String = "",
    val timeline:Long = System.currentTimeMillis(),
)