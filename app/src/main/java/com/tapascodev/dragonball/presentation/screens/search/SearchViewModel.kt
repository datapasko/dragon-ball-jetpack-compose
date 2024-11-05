package com.tapascodev.dragonball.presentation.screens.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.domain.usecase.search.SearchCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCharacterUseCase: SearchCharacterUseCase
): ViewModel() {
    private val _characters = MutableStateFlow<Resource<List<CharacterModel>>>(Resource.Loading)
    val characters : StateFlow<Resource<List<CharacterModel>>>
        get() = _characters

    fun searchCharacter(name:String) = viewModelScope.launch {
        Log.d("messi", name)
        _characters.value = searchCharacterUseCase.invoke(name)
    }

}