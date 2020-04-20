package com.mreigar.presentation.mapper

interface Mapper<in D, out V> {
    fun mapToView(domainEntity: D): V
}