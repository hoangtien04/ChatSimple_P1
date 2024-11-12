package com.example.chatsimple_firebase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen (navController: NavController){
    var viewModel: AccountViewModel = viewModel(
        modelClass = AccountViewModel::class.java
    )
    var state = viewModel.state
    OutlinedTextField(
        value = state.email,
        onValueChange = viewModel::onChangeEmail,
        placeholder = { Text(text = "Email")},
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    )
    OutlinedTextField(
        value = state.password,
        onValueChange = viewModel::onChangePassword,
        placeholder = { Text(text = "Password")},
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Đăng nhập")},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                  IconButton(onClick = { navController.popBackStack() }) {
                      Icon(
                          imageVector = Icons.Default.ArrowBack, contentDescription = "",tint = Color.White
                      )
                  }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text="Chat Simple", fontWeight = FontWeight.ExtraBold, fontSize = 50.sp)
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(text = "Email")},
                modifier = Modifier.padding(20.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(text = "Password")},
                modifier = Modifier.padding(20.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                onClick = {
                    //3
                    viewModel.SignIn()
                    if(state.success){
                        navController.navigate(NavRoute.HOME.route)

                    }
                }

            ){
                Text(text = "Đăng nhập", fontSize = 20.sp,fontWeight = FontWeight.Bold)
            }
        }
    }
}
