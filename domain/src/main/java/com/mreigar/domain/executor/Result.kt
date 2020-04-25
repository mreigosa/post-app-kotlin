package com.mreigar.domain.executor

sealed class Result<out T>(open val dataStatus: DataStatus = DataStatus.ERROR)

data class Success<out T>(val data: T, override val dataStatus: DataStatus) : Result<T>(dataStatus)
data class Error(val exception: Exception = Exception()) : Result<Nothing>()
object NoData : Result<Nothing>()

enum class DataStatus {
    LOCAL, REMOTE, ERROR
}