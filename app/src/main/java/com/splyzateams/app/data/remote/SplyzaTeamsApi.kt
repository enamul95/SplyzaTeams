package com.splyzateams.app.data.remote

import com.splyzateams.app.data.dto.TeamsDto
import com.splyzateams.app.data.dto.TeamsInvitesDto
import com.splyzateams.app.domain.model.InvitesModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SplyzaTeamsApi {

    @GET("v1/teams/{teamId}")
    suspend fun getTeams(@Path("teamId") teamId:String):TeamsDto

    @POST("v1/teams/{teamId}/invites")
    suspend fun inviteMember(@Path("teamId") teamId:String, @Body model: InvitesModel):TeamsInvitesDto


}