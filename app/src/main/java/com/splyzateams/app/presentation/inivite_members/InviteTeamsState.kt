package com.splyzateams.app.presentation.inivite_members

import com.splyzateams.app.domain.model.Teams

data class InviteTeamsState(
    val isLoading: Boolean = false,
    val url:String = "",
    val error:String = ""
)
