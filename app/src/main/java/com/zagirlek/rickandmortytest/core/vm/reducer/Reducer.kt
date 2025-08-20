package com.zagirlek.rickandmortytest.core.vm.reducer

import com.zagirlek.rickandmortytest.core.vm.ViewState

interface Reducer <VS: ViewState, M: Mutation> {
    fun reduce(currentState: VS, mutation: M): VS
}