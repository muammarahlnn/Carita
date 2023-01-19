package com.ardnn.carita.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file BaseSuspendUseCase, 19/01/2023 16.56 by Muammar Ahlan Abimanyu
 */
abstract class BaseSuspendUseCase<Params>(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    abstract suspend fun buildUseCase(params: Params): Flow<Unit>

    suspend fun execute(
        params: Params,
        onStart: () -> Unit = {},
        onSuccess: () -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onCompletion: () -> Unit = {},
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch {
            buildUseCase(params)
                .flowOn(defaultDispatcher)
                .onStart {
                    onStart.invoke()
                }.onEach {
                    onSuccess.invoke()
                }.catch {
                    onError.invoke(it)
                }.onCompletion {
                    onCompletion.invoke()
                }
        }
    }
}