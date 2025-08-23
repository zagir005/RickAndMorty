package com.zagirlek.rickandmortytest.core.cmp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.zagirlek.rickandmortytest.core.cmp.reducer.Mutation
import com.zagirlek.rickandmortytest.core.cmp.reducer.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BaseComponent <VS: ViewState, M: Mutation, A: Action, R: Reducer<VS, M>>(
    private val reducer: R,
    componentContext: ComponentContext
): ComponentContext by componentContext {

    protected val componentScope = coroutineScope(context = SupervisorJob() + Dispatchers.Main).also {
        doOnDestroy { it.cancel() }
    }

    fun M.reduce(state: MutableValue<VS>){
        state.update { reducer.reduce(currentState = state.value, mutation = this@reduce ) }
    }

    abstract fun action(action: A)
}