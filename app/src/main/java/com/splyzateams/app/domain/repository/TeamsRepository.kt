package com.splyzateams.app.domain.repository

import com.splyzateams.app.data.dto.TeamsDto
import com.splyzateams.app.data.dto.TeamsInvitesDto

interface TeamsRepository {

    suspend fun getTeams(teamId:String):TeamsDto

    suspend fun inviteMember(teamId:String):TeamsInvitesDto

}