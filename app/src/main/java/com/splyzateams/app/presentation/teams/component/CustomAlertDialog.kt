package com.splyzateams.app.presentation.teams.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.splyzateams.app.data.dto.PermissionDto
import com.splyzateams.app.data.dto.permissionList
import com.splyzateams.app.domain.model.Teams


@Composable
fun CustomAlertDialog(
    onDismiss: () -> Unit,
    onItemClick: (PermissionDto) -> Unit,
    teams: Teams,
) {

    Dialog(
        onDismissRequest = {
            onDismiss()
        }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {

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

                   if(teams.plan.supporterLimit == 0 && item.role == "readonly"){
                       // hide supporter
                    }else{
                       Column(
                           modifier = Modifier
                               .padding(all = 10.dp)
                               .background(
                                   Color.White
                               )
                               .fillMaxWidth()
                               .clickable() {
                                   onItemClick(item)
                               },


                           ) {
                           Text(
                               item.roleDes, fontSize = 18.sp,
                               textAlign = TextAlign.Center,
                               color = Color.Blue,
                               modifier = Modifier
                                   .fillMaxSize()
                                   .wrapContentHeight()
                                   .padding(10.dp)
                           )

                           Divider(
                               color = Color.LightGray,
                               modifier = Modifier
                                   .fillMaxWidth(),
                               thickness = 1.dp,
                               startIndent = 20.dp
                           )


                       }
                    }





                }

            }


        }

    }
}