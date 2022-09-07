package com.splyzateams.app.presentation.teams.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.splyzateams.app.common.Constrants
import com.splyzateams.app.domain.model.InvitesModel
import com.splyzateams.app.domain.model.Teams
import com.splyzateams.app.presentation.inivite_members.InviteTeamViewModel


@Composable
fun TextFieldWithIcons(
    teams: Teams,
    inviteTeamViewModel: InviteTeamViewModel = hiltViewModel()
) {
    var permissionTextState by remember { mutableStateOf("") }
    val state = inviteTeamViewModel.state.value
    var role = ""
    var showCustomDialog by remember {
        mutableStateOf(false)
    }

    if (showCustomDialog) {

        CustomAlertDialog(
            {
                showCustomDialog = !showCustomDialog
            },
            {

                if("manager" == it.role ||"editor"== it.role||"member"== it.role){
                    var currentMember = (teams.members?.administrators ?: 0) + (teams.members?.managers
                        ?: 0) + (teams.members?.editors ?: 0) + (teams.members?.members ?: 0)
                    if(currentMember <= (teams.plan?.memberLimit ?: 0)){
                        if("readonly" == it.role){
                            showCustomDialog = true
                        }else{
                            permissionTextState = it.roleDes
                            role = it.role
                            showCustomDialog = false
                            teams.id?.let { it1 -> changeRole(inviteTeamViewModel,role, it1) }

                        }

                    }
                } else if("readonly" == it.role){
                    if((teams.plan?.supporterLimit ?: 0)>=(teams.members?.supporters ?: 0)){
                        showCustomDialog = true
                    }else{
                        permissionTextState = it.roleDes
                        role = it.role
                        showCustomDialog = false
                        teams.id?.let { it1 -> changeRole(inviteTeamViewModel,role, it1) }

                    }
                }

            },
            teams
        )


    }

    return OutlinedTextField(
        value = permissionTextState,
        onValueChange = {
            permissionTextState = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .clickable {
                showCustomDialog = !showCustomDialog
            },
        enabled = false,
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown, contentDescription = null,
                tint = Color.Gray
            )
        },

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = Color.LightGray,
            cursorColor = Color.LightGray
        ),
        label = { Text(text = Constrants.TEAMS_SELECT_PERMISSION_LABEL, color = Color.LightGray) },
    )

}

fun changeRole(inviteTeamViewModel:InviteTeamViewModel,role:String,teamId:String){

    var model = InvitesModel();
    model.role = role
    inviteTeamViewModel.inviteTeamsMember(teamId,model)
}