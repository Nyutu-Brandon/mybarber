package com.example.mybarber.ui.theme.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mybarber.R
import com.example.mybarber.data.AuthViewModel
import com.example.mybarber.navigation.ROUTE_LOGIN
import com.example.mybarber.ui.theme.screens.login.LoginScreen

@Composable
fun SignupScreen(navController: NavController){
    val context = LocalContext.current
    val authViewModel: AuthViewModel = viewModel()
    val isLoading by authViewModel.isLoading.collectAsState()
    var email by remember {
        mutableStateOf(value = "")
    }
    var username by remember {
        mutableStateOf(value = "")
    }
    var password by remember {
        mutableStateOf(value = "")
    }
    var confirmpassword by remember {
        mutableStateOf(value = "")
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .height(80.dp),
            painter = painterResource(id = R.drawable.loginpic1),
            contentDescription = "Login Image"
        )


        Spacer(modifier = androidx.compose.ui.Modifier.height(30.dp))
        Text(text = "Register Here",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = androidx.compose.ui.Modifier
                .background(Color.Black)
                .padding(20.dp)
                .fillMaxWidth(),
        )
        Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))
        OutlinedTextField(
            value = username,
            onValueChange = {newUsername -> username = newUsername},
            label = { Text(text = "Enter your username") },
            placeholder = { Text(text = "Please enter your username") },
        )
        Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {newEmail -> email = newEmail},
            label = { Text(text = "Enter Email") },
            placeholder = { Text(text = "Please enter Email") },
        )
        Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {newPassword -> password = newPassword},
            label = { Text(text = "Enter Password") },
            placeholder = { Text(text = "Please enter Password") },
        )
        Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))
        OutlinedTextField(
            value = confirmpassword,
            onValueChange = {newConfirmpassword -> confirmpassword = newConfirmpassword},
            label = { Text(text = "Confirm Password") },
            placeholder = { Text(text = "Please enter  confirm Password") },
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                authViewModel.signup(username,email,password,confirmpassword,navController,context)


            },
            enabled = !isLoading,
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)

        ) {
            if (isLoading){
                CircularProgressIndicator(color = Color.Black, strokeWidth = 4.dp)

            }else{
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "REGISTER HERE"
                )
            }


        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Already have an account?")
        Spacer(modifier = Modifier.height(5.dp))
        ClickableText(
            text = AnnotatedString("Click here to Login"),
            onClick = {
                navController.navigate(ROUTE_LOGIN)

            },
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        )

    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    SignupScreen(rememberNavController())

}