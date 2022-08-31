package com.splyzateams.app.domain.use_case.get_teams

import com.splyzateams.app.common.Resource
import com.splyzateams.app.data.dto.toTeams
import com.splyzateams.app.domain.model.Teams
import com.splyzateams.app.domain.repository.TeamsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetTeams @Inject constructor(
    private val repository: TeamsRepository
) {
    // operator fun invoke use GetTeams as a function
    // Flow use to meet multiple tasking like success, error, loading
    operator fun invoke(teamId:String):Flow<Resource<Teams>> = flow {

        try {
            emit(Resource.Loading())
            val teams = repository.getTeams(teamId).toTeams()
            emit(Resource.Success(teams))

        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occured"))
        }catch (e:IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))

        }
    }
}