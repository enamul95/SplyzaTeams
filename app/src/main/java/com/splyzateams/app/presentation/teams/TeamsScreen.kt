package com.splyzateams.app.presentation.teams

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.DrawerValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.splyzateams.app.common.Constrants
import com.splyzateams.app.presentation.Screen
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.splyzateams.app.data.dto.PermissionDto
import com.splyzateams.app.presentation.teams.component.TeamsMainContent
import com.splyzateams.app.presentation.teams.component.TextFieldWithIcons

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
            TeamsMainContent(state)
        },
        backgroundColor = Color(0xFFEDEAE0)

    )

}




