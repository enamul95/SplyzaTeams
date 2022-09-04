package com.splyzateams.app.presentation.inivite_members

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splyzateams.app.common.Constrants
import com.splyzateams.app.common.Resource
import com.splyzateams.app.domain.model.InvitesModel
import com.splyzateams.app.domain.use_case.get_teams.GetTeams
import com.splyzateams.app.domain.use_case.invite_teams.InviteTeams
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class InviteTeamViewModel @Inject constructor(
    private val inviteTeams: InviteTeams,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(InviteTeamsState())
    val state:State<InviteTeamsState> = _state

//    init {
//        savedStateHandle.get<String>("url")?.let { url ->
//           Log.e("--",url)
//        }
//    }

    fun inviteTeamsMember(teamId: String, model: InvitesModel){
        inviteTeams(teamId,model).onEach {  result ->
            when(result){
                is Resource.Success -> {
                    _state.value = InviteTeamsState(url = result.data?.url ?:"")
                    Log.e(" it.value.url", result.data?.url ?:"")
                }
                is Resource.Error -> {
                   _state.value = InviteTeamsState(error = result.message?: "An Unexpected Error Occured")
                }
                is Resource.Loading -> {
                    _state.value = InviteTeamsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}