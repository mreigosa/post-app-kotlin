package com.mreigar.localstorage.mapper

interface Mapper<M, E> {
    fun mapFromDatabase(databaseEntity: M): E
    fun mapToDatabase(dataEntity: E): M
}