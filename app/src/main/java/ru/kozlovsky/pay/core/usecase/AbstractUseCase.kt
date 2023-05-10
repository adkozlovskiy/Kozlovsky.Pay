package ru.kozlovsky.pay.core.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.kozlovsky.pay.core.result.Result
import ru.kozlovsky.pay.core.result.doOnSuccess
import ru.kozlovsky.pay.core.result.failure.ExceptionResolver
import ru.kozlovsky.pay.core.result.failure.Failure
import ru.kozlovsky.pay.di.IODispatcher

abstract class AbstractUseCase<in P, R, out F : Failure>(
    @IODispatcher
    private val coroutineDispatcher: CoroutineDispatcher,
    private val exceptionResolver: ExceptionResolver<F>? = null,
) {

    suspend operator fun invoke(params: P): Result<R, F> {
        return Result.catch(exceptionResolver) {
            withContext(coroutineDispatcher) {
                doInvoke(params)
            }
        }.doOnSuccess { doOnSuccess(it) }
    }

    protected abstract suspend fun doInvoke(params: P): R

    protected open suspend fun doOnSuccess(result: R) {
        // do nothing by default
    }
}