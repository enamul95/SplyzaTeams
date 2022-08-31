package com.splyzateams.app.data.repository

import com.splyzateams.app.data.dto.TeamsDto
import com.splyzateams.app.data.dto.TeamsInvitesDto
import com.splyzateams.app.data.remote.SplyzaTeamsApi
import com.splyzateams.app.domain.repository.TeamsRepository
import javax.inject.Inject

class TeamsRepositoryImpl @Inject constructor(
    private val api:SplyzaTeamsApi
):TeamsRepository{

    override suspend fun getTeams(teamId: String): TeamsDto {
        return api.getTeams(teamId)
    }

    override suspend fun inviteMember(teamId: String): TeamsInvitesDto {
        return api.inviteMember(teamId)
    }
}