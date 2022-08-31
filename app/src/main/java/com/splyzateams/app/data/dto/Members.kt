package com.splyzateams.app.data.dto

data class Members(
    val administrators: Int,
    val editors: Int,
    val managers: Int,
    val members: Int,
    val supporters: Int,
    val total: Int
)