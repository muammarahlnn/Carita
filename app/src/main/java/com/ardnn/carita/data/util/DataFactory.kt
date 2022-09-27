package com.ardnn.carita.data.util

interface DataFactory<T> {

    fun createData(@Source source: String): T
}