package com.splyzateams.app.domain.model

import com.splyzateams.app.data.dto.Members
import com.splyzateams.app.data.dto.Plan

data class Teams(
    val id: String?,
    val members: Members?,
    val plan: Plan?
)
