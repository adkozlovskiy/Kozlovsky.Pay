package ru.kozlovsky.pay.core.result

import ru.kozlovsky.pay.core.result.failure.ExceptionResolver
import ru.kozlovsky.pay.core.result.failure.Failure

sealed class Result<out R, out F : Failure> {
    /**
     * Success result with specified type data.
     */
    data class Success<R>(val data: R) : Result<R, Nothing>()

    /**
     * Failure result with resolved failure : FailureInfo.
     * @see Failure
     */
    data class ResolvedFailure<F : Failure>(val info: F) : Result<Nothing, F>()

    /**
     * Failure result with unresolved failure.
     *
     * Resolve it by yourself or specify the [ExceptionResolver] in [Result.catch] function.
     */
    class UnresolvedFailure(val ex: Exception) : Result<Nothing, Nothing>()

    /* Help functions */
    fun isSuccessful() = this is Success<*>
    fun isFailure() = this is ResolvedFailure<*> || this is UnresolvedFailure

    override fun toString(): String {
        return when (this) {
            is Success -> "Success with ${this.data}"
            is ResolvedFailure -> "Failure with ${this.info}"
            is UnresolvedFailure -> "Unresolved failure ${this.ex}"
        }
    }

    companion object {
        /**
         * Proceed [function] to [Result].
         *
         * If function call was without exceptions, [Result.Success] will be returned.
         *
         * If exception was thrown and resolver specified, [Result.ResolvedFailure] with [F] type will be returned.
         *
         * Otherwise [Result.UnresolvedFailure] will be returned.
         */
        inline fun <R, F : Failure> catch(
            resolver: ExceptionResolver<F>? = null,
            function: () -> R,
        ): Result<R, F> {
            return try {
                Success(data = function())
            } catch (ex: Exception) {
                // If resolver wasn't provided.
                if (resolver == null) {
                    return UnresolvedFailure(ex)
                }

                return ResolvedFailure(info = resolver.resolve(ex))
            }
        }
    }
}

/**
 * Function which invoke [function] block only when
 * Result is success.
 * @param function function will be invoked when success.
 * @return result
 */
inline fun <R, F : Failure> Result<R, F>.doOnSuccess(
    function: (R) -> Unit,
): Result<R, F> {
    if (this is Result.Success) {
        function.invoke(this.data)
    }
    return this
}

/**
 * Function which invoke [function] block only when
 * Result is failure.
 * @param function function will be invoked when failure.
 * @return result
 */
inline fun <R, F : Failure> Result<R, F>.doOnFailure(
    function: () -> Unit,
): Result<R, F> {
    if (this is Result.UnresolvedFailure || this is Result.ResolvedFailure) {
        function.invoke()
    }
    return this
}