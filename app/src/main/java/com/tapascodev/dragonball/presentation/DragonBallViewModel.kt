package com.tapascodev.dragonball.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.domain.model.Resource
import com.tapascodev.dragonball.domain.usecase.GetAllCharactersUseCase
import com.tapascodev.dragonball.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DragonBallViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _character = MutableStateFlow<Resource<CharacterModel>> (Resource.Loading)
    val character: StateFlow<Resource<CharacterModel>>
        get()  = _character

    private val _characters = MutableStateFlow<Resource<Flow<PagingData<CharacterModel>>>>(Resource.Loading)
    val characters : StateFlow<Resource<Flow<PagingData<CharacterModel>>>>
        get() = _characters

    init {
        getAllCharacters()
    }

    private fun getAllCharacters() = viewModelScope.launch {
        _characters.value = getAllCharactersUseCase.invoke()
    }

    fun getCharacter (id: Int) = viewModelScope.launch {
        _character.value = getCharacterUseCase.invoke(id)
    }
}