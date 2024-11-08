package com.example.chatsimple_firebase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WelcomeScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Chat simple", fontWeight = FontWeight.Bold,fontSize = 50.sp)
        Button(
            onClick = { navController.navigate(NavRoute.LOGIN.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(20)
        ) {
            Text(text = "Đăng nhập",fontSize = 20.sp,fontWeight = FontWeight.Bold)
        }
        Button(
            onClick = {  navController.navigate(NavRoute.REGISTER.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(20)
        ) {
            Text(text = "Đăng ký",fontSize = 20.sp,fontWeight = FontWeight.Bold)
        }
    }
}