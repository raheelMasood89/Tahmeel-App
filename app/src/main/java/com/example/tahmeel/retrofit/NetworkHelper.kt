package com.example.tahmeel.retrofit

import com.example.tahmeel.util.DataState
import com.example.tahmeel.util.ErrorResponse
import java.io.IOException
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException

abstract class NetworkHelper<in P, out R>() {

    fun execute(parameters: P?): Flow<DataState<R>> {
        return buildUseCase(parameters).buffer().catch { e ->
            when (e) {
                is IOException -> emit(DataState.NetworkError)
                is HttpException -> {
                    val code = e.code()
                    val errorResponse = convertErrorBody(e)
                    emit(DataState.GenericError(code, errorResponse))
                }
                else -> {
                    emit(DataState.GenericError(null, null))
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }

    abstract fun buildUseCase(parameters: P?): Flow<DataState<R>>
}