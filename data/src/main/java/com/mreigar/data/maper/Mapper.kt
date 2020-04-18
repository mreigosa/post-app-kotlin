package com.mreigar.data.maper

interface Mapper<E, D> {
    fun mapFromEntity(type: E): D
    fun mapToEntity(type: D): E
}