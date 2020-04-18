package com.mreigar.network.mapper

interface Mapper<in M, out E> {
    fun mapFromRemote(remoteEntity: M): E
}