package com.splyzateams.app.data.dto

data class PermissionDto(

    val role: String,
    val roleDes: String
)


val permissionList = listOf(
    PermissionDto("manager", "Coach"),
    PermissionDto("editor", "Player Coach"),
    PermissionDto("member", "Player"),
    PermissionDto("readonly", "Supporter"),
)