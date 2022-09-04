package com.splyzateams.app.presentation.teams

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.splyzateams.app.common.Constrants
import com.splyzateams.app.presentation.Screen
import com.splyzateams.app.presentation.teams.component.TeamsMainContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TeamsScreen(
    navController: NavController,
    teamViewModel: TeamsViewModel = hiltViewModel()
) {
    val state = teamViewModel.state.value

    Scaffold(

        topBar = {
            TopAppBar(
                elevation = 4.dp,
                title = {
                    Text(
                        Constrants.TEAMS_TITLE,
                        color = Color.White
                    )

                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.HomeScreen.route)
                        },
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = Constrants.BACK,
                            tint = Color.White
                        )
                    }

                },
                backgroundColor = Color.Blue

            )
        },


        content = {
            TeamsMainContent(state,navController)
        },
        backgroundColor = Color(0xFFEDEAE0)

    )

}




