package com.mreigar.data.maper

interface Mapper<E, D> {
    fun mapFromEntity(dataEntity: E): D
    fun mapToEntity(domainEntity: D): E
}