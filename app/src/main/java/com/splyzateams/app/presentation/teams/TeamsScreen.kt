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
            MainContent(state)
        },
        backgroundColor = Color(0xFFEDEAE0)

    )

}

@Composable
fun MainContent(state: TeamsState) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        state.teams?.let { teams ->

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {


                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        var currentMember =
                            teams.members.administrators + teams.members.managers + teams.members.editors + teams.members.members

                        Text(
                            text = Constrants.TEAMS_CURRENT_MEMBERS + " " + currentMember,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.weight(1.2f)
                        )
                        Text(
                            text = Constrants.LIMIT + " " + teams.plan.memberLimit,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(.8f)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {

                        Text(
                            text = Constrants.TEAMS_CURRENT_SUPPORTERS + " " + teams.members.supporters,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.weight(1.2f)
                        )
                        Text(
                            text = Constrants.LIMIT + " " + teams.plan.supporterLimit,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(.8f)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = Constrants.TEAMS_INVITE_PERMISSION,
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )

                    TextFieldWithIcons()


                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}


@Composable
fun TextFieldWithIcons() {
    val context = LocalContext.current


    //LargerDialog(setShowDialog)
    var text by remember { mutableStateOf(TextFieldValue("")) }


    var showCustomDialog by remember {
        mutableStateOf(false)
    }

    if (showCustomDialog) {
        CustomAlertDialog({
            showCustomDialog = !showCustomDialog
        }, {
            val activity = (context as? Activity)
            activity?.finish()
        })
    }

    return OutlinedTextField(
        value = text,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .clickable {
                // Toast.makeText(context,"clicl",Toast.LENGTH_LONG).show()
                showCustomDialog = !showCustomDialog
                // setShowDialog(true)
            },
        enabled = false,
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown, contentDescription = null,
                tint = Color.Gray
            )
        },
        onValueChange = {
            text = it
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = Color.LightGray,
            cursorColor = Color.LightGray
        ),
        label = { Text(text = "Select Invitation Permission", color = Color.LightGray) },
    )


}


@Composable
fun CustomAlertDialog(onDismiss: () -> Unit, onExit: () -> Unit) {

    Dialog(
        onDismissRequest = { onDismiss() }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {

        val permissionList = listOf(
            PermissionDto("manager", "Coach"),
            PermissionDto("editor", "Coach"),
            PermissionDto("member", "Player"),
            PermissionDto("readonly", "Supporter"),
        )

        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {

            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {

                items(permissionList.size) {
                    val item = permissionList[it]
                    // Text(text = item.roleDes)
                    //  Divider()

                    Column(
                        modifier = Modifier
                            .padding(all = 5.dp)
                            .background(Color.White)
                            .fillMaxWidth()
                    ) {
                        Text(
                            item.roleDes, fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            color = Color.Blue,
                            modifier = Modifier
                                //.background(Color.Black)
                                .fillMaxSize()
                                .wrapContentHeight()
                                .padding(5.dp)
                        )
                        /* Text(item.role, color = Color.Gray,
                            modifier = Modifier
                                .padding(10.dp)

                        )
                        */
                        Divider()
                    }

                }

            }


        }

    }
}


