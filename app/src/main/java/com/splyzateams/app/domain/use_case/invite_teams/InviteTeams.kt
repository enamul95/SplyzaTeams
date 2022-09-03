package com.splyzateams.app.domain.use_case.invite_teams

import com.splyzateams.app.common.Resource
import com.splyzateams.app.data.dto.toTeams
import com.splyzateams.app.data.dto.toTeamsInvites
import com.splyzateams.app.domain.model.InvitesModel
import com.splyzateams.app.domain.model.Teams
import com.splyzateams.app.domain.model.TeamsInvites
import com.splyzateams.app.domain.repository.TeamsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class InviteTeams @Inject constructor(
    private val repository: TeamsRepository
) {
    // operator fun invoke use GetTeams as a function
    // Flow use to meet multiple tasking like success, error, loading
    operator fun invoke(teamId: String, model: InvitesModel):Flow<Resource<TeamsInvites>> = flow {

        try {
            emit(Resource.Loading())
            val teams = repository.inviteMember(teamId,model).toTeamsInvites()
            emit(Resource.Success(teams))

        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occured"))
        }catch (e:IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))

        }
    }
}