package com.splyzateams.app.presentation.teams

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splyzateams.app.common.Constrants
import com.splyzateams.app.common.Resource
import com.splyzateams.app.domain.use_case.get_teams.GetTeams
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val getTeams: GetTeams,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(TeamsState())
    val state:State<TeamsState> = _state
    init {
        savedStateHandle.get<String>(Constrants.TEAM_ID)?.let { teamId ->
            getTeamsMember(teamId)
        }
    }

    private fun getTeamsMember(teamId:String){
        getTeams(teamId).onEach {  result ->
            when(result){
                is Resource.Success -> {
                    _state.value = TeamsState(teams = result.data)
                }
                is Resource.Error -> {
                   _state.value = TeamsState(error = result.message?: "An Unexpected Error Occured")
                }
                is Resource.Loading -> {
                    _state.value = TeamsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}