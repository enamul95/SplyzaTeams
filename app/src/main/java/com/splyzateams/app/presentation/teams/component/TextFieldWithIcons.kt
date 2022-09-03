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
                    var currentMember = teams.members.administrators + teams.members.managers + teams.members.editors + teams.members.members
                    if(currentMember <= teams.plan.memberLimit){
                        if("readonly" == it.role){
                            showCustomDialog = true
                        }else{
                            permissionTextState = it.roleDes
                            role = it.role
                            showCustomDialog = false
                            changeRole(inviteTeamViewModel,role,teams.id)

                        }

                    }
                } else if("readonly" == it.role){
                    if(teams.members.supporters <= teams.plan.supporterLimit){
                        showCustomDialog = true
                    }else{
                        permissionTextState = it.roleDes
                        role = it.role
                        showCustomDialog = false
                        changeRole(inviteTeamViewModel,role,teams.id)

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
        label = { Text(text = "Select Invitation Permission", color = Color.LightGray) },
    )

}

fun changeRole(inviteTeamViewModel:InviteTeamViewModel,role:String,teamId:String){

    var model = InvitesModel();
    model.role = role
    inviteTeamViewModel.inviteTeamsMember(teamId,model)
}