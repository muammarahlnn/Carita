package com.ardnn.carita.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file BaseUseCase, 18/01/2023 14.09 by Muammar Ahlan Abimanyu
 */
abstract class BaseUseCase<Params, T>(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    abstract fun buildUseCase(params: Params): Flow<T>

    fun execute(
        params: Params,
        onStart: () -> Unit = {},
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onCompletion: () -> Unit = {},
        coroutineScope: CoroutineScope
    ) {
        buildUseCase(params)
            .flowOn(defaultDispatcher)
            .onStart {
                onStart.invoke()
            }.onEach {
                onSuccess.invoke(it)
            }.catch {
                onError.invoke(it)
            }.onCompletion {
                onCompletion.invoke()
            }.launchIn(coroutineScope)
    }
}