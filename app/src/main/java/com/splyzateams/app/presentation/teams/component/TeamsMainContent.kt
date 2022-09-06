package com.splyzateams.app.presentation.teams.component

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.splyzateams.app.common.Constrants
import com.splyzateams.app.presentation.Screen
import com.splyzateams.app.presentation.inivite_members.InviteTeamViewModel
import com.splyzateams.app.presentation.teams.TeamsState


@Composable
fun TeamsMainContent(
    state: TeamsState,
    navController: NavController,
    inviteTeamViewModel: InviteTeamViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val inviteState = inviteTeamViewModel.state.value
    val clipboardManager: ClipboardManager = LocalClipboardManager.current


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
                            (teams.members?.administrators ?: 0) + (teams.members?.managers ?: 0) + (teams.members?.editors
                                ?: 0) + (teams.members?.members ?: 0)

                        Text(
                            text = Constrants.TEAMS_CURRENT_MEMBERS + " " + currentMember,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.weight(1.2f)
                        )
                        Text(
                            text = Constrants.LIMIT + " " + (teams.plan?.memberLimit ?: 0),
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(.8f)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    var visible by remember { mutableStateOf(true) }

                    if ((teams.plan?.supporterLimit ?: 0) == 0) {
                        visible = false
                    }

                    if (visible) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {

                            Text(
                                text = Constrants.TEAMS_CURRENT_SUPPORTERS + " " + (teams.members?.supporters
                                    ?: 0),
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.weight(1.2f)
                            )
                            Text(
                                text = Constrants.LIMIT + " " + (teams.plan?.supporterLimit ?: 0),
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.End,
                                modifier = Modifier.weight(.8f)
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }


                    Text(
                        text = Constrants.TEAMS_INVITE_PERMISSION,
                        style = MaterialTheme.typography.body1
                    )

                    TextFieldWithIcons(teams)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = Constrants.TEAMS_URL_DES,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    //Share Qr button begin
                    Button(
                        onClick = {

                            if(inviteState.url.isNotBlank()){
                                navController.currentBackStackEntry?.savedStateHandle?.apply {
                                    set(Constrants.QR_URL, inviteState.url)
                                }
                                navController.navigate(Screen.QRScreen.route)
                            }else{
                                Toast.makeText(context, Constrants.TEAMS_SELECT_PERMISSION, Toast.LENGTH_LONG).show()
                            }

                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        shape = RoundedCornerShape(percent = 20)
                    )
                    {
                        Text(
                            text = Constrants.TEAMS_SHARE_QR_BUTTON,
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                    // Copy the link Button
                    Button(
                        onClick = {
                            if(inviteState.url.isNotBlank()){
                                inviteState.url.let {
                                    clipboardManager.setText(AnnotatedString(("" + inviteState.url)))
                                    Toast.makeText(context, Constrants.TEAMS_COPY_BUTTON, Toast.LENGTH_LONG).show()
                                }
                            }else{
                                Toast.makeText(context, Constrants.TEAMS_SELECT_PERMISSION, Toast.LENGTH_LONG).show()
                            }

                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        shape = RoundedCornerShape(percent = 20)
                    )
                    {
                        Text(
                            text = Constrants.TEAMS_COPY_BUTTON,
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    }

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