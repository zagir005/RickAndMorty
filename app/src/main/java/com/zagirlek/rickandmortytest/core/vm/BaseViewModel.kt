package com.zagirlek.rickandmortytest.core.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zagirlek.rickandmortytest.core.vm.reducer.Mutation
import com.zagirlek.rickandmortytest.core.vm.reducer.Reducer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel <VS: ViewState, M: Mutation, R: Reducer<VS, M>>(
    private val reducer: R,
): ViewModel() {

    fun M.reduce(state: MutableStateFlow<VS>){
        state.update { reducer.reduce(currentState = state.value, mutation = this@reduce ) }
    }
}