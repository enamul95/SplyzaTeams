package com.splyzateams.app.presentation.teams.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.splyzateams.app.common.Constrants
import com.splyzateams.app.presentation.teams.TeamsState


@Composable
fun TeamsMainContent(state: TeamsState) {
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

                        var currentMember = teams.members.administrators + teams.members.managers + teams.members.editors + teams.members.members

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
                    var visible by remember { mutableStateOf(true) }

                    if(teams.plan.supporterLimit == 0){
                        visible = false
                    }

                    if(visible){
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
                    }



                    Text(
                        text = Constrants.TEAMS_INVITE_PERMISSION,
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )

                    TextFieldWithIcons(teams)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = Constrants.TEAMS_URL_DES,
                        textAlign = TextAlign.Center,
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    //Share Qr button begin
                    Button(
                        onClick = {
                            //your onclick code
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
                            //your onclick code
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