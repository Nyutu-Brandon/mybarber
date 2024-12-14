package com.example.mybarber.ui.theme.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mybarber.R
import com.example.mybarber.navigation.ROUTE_CLIENT_RESERVATION
import com.example.mybarber.navigation.ROUTE_LOGIN
import com.example.mybarber.navigation.ROUTE_VIEW_CLIENT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        TopAppBar( { },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(ROUTE_LOGIN)
                }) {
                    Icon(imageVector = Icons.Filled.Home,
                        contentDescription = "Home"
                    )
                }
            }

        )
        Spacer(modifier = Modifier.height(20.dp))
        Row (modifier = Modifier.wrapContentWidth()){

            Text(text = "Book your session here and also check your reservation history",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif,
                color= Color.Black)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row (modifier = Modifier.wrapContentWidth()){
            Card (modifier = Modifier
                .padding(10.dp)
                .clickable {
                    navController.navigate(ROUTE_CLIENT_RESERVATION)
                },
                shape = RoundedCornerShape(40.dp),
                elevation = CardDefaults.cardElevation(20.dp)
            ){
                Box (modifier = Modifier.height(150.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.booksession),
                        contentDescription = "New Client")
                    Box (modifier = Modifier
                        .matchParentSize()
                        .padding(10.dp),
                        contentAlignment = Alignment.Center){
                        Text(color = Color.Blue,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp,
                            text = "")

                    }

                }


            }
            Card (modifier = Modifier
                .padding(10.dp)
                .clickable {
                    navController.navigate(ROUTE_VIEW_CLIENT)

                },
                shape = RoundedCornerShape(40.dp),
                elevation = CardDefaults.cardElevation(20.dp)
            ){
                Box (modifier = Modifier.height(150.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.reservationhistory
                        ),
                        contentDescription = "New Client")
                    Box (modifier = Modifier
                        .matchParentSize()
                        .padding(10.dp),
                        contentAlignment = Alignment.Center){
                        Text(color = Color.Blue,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp,
                            text = "")

                    }

                }


            }


        }


        }



    }


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashBoardPreview(){
    Dashboard(rememberNavController())
}