package com.mreigar.localstorage.mapper

interface Mapper<M, E> {
    fun mapFromDatabase(type: M): E
    fun mapToDatabase(type: E): M
}