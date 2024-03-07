package com.klaudia.graphqlpracticeapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klaudia.graphqlpracticeapp.domain.Character
import com.klaudia.graphqlpracticeapp.domain.DetailedCharacter
import com.klaudia.graphqlpracticeapp.domain.GetCharacterUseCase
import com.klaudia.graphqlpracticeapp.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterUseCase: GetCharacterUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()
    init {
        viewModelScope.launch{
            _state.update {
                it.copy(isLoading = true)
            }
            _state.update {
                it.copy(
                    characters = getCharactersUseCase.execute().characters.results,
                    isLoading = false
                )
            }
        }
    }
    fun selectCharacter(code: String){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCharacter = getCharacterUseCase.execute(code)
                )
            }
        }
    }
    fun dismissCharacterDialog(){
        _state.update {
            it.copy(
                selectedCharacter = null
            )
        }
    }
    data class CharactersState(
        val characters: List<Character> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCharacter: DetailedCharacter? = null
        )
}

