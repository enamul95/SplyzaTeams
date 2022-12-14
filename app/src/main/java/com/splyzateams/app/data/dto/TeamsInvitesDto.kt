package com.splyzateams.app.data.dto

import com.splyzateams.app.domain.model.TeamsInvites


data class TeamsInvitesDto(
    val id: Int,
    val teamId: String,
    val role: String,
    val url: String
)

fun TeamsInvitesDto.toTeamsInvites():TeamsInvites{
    return TeamsInvites(url=url)
}



