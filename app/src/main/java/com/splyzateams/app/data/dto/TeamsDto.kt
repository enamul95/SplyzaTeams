package com.splyzateams.app.data.dto

import com.splyzateams.app.domain.model.Teams

data class TeamsDto(
    val id: String,
    val members: Members,
    val plan: Plan
)

fun TeamsDto.toTeams():Teams{
    return Teams(
        id=id,
        members = members,
        plan = plan
    )
}