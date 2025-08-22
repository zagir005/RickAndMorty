package com.zagirlek.rickandmortytest.core.cmp.reducer

import com.zagirlek.rickandmortytest.core.cmp.ViewState

interface Reducer <VS: ViewState, M: Mutation> {
    fun reduce(currentState: VS, mutation: M): VS
}