package com.splyzateams.app.presentation.home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.splyzateams.app.common.Constrants
import com.splyzateams.app.presentation.Screen

@Composable
fun HomeScreen(
    navController: NavController

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        Button(
            onClick = {
                navController.navigate(Screen.TeamsScreen.route)
            }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
            modifier = Modifier
                .padding(15.dp),
            shape = RoundedCornerShape(percent = 20)
        )
        {
            Text(
                text = Constrants.HOME_INVITE,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(5.dp)
            )
        }
    }

}