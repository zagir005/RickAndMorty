package com.zagirlek.core.cmp.reducer

import com.zagirlek.core.cmp.ViewState

interface Reducer <VS: ViewState, M: Mutation> {
    fun reduce(currentState: VS, mutation: M): VS
}