package com.erazero1.compose_di_example.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}