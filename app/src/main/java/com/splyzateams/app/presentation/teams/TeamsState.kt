package com.splyzateams.app.presentation.teams

import com.splyzateams.app.domain.model.Teams

data class TeamsState(
    val isLoading: Boolean = false,
    val teams: Teams? = null,
    val error:String = ""
)
