package com.erazero1.compose_di_example.data.model

data class PaginatedResponse<T>(
    val info: InfoDto,
    val results: List<T>
)

data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)