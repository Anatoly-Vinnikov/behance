package com.avinnikov.behance.common.equality

interface Equality<E> {
    fun getIdentityEquality(): (E, E) -> Boolean

    fun getContentEquality(): (E, E) -> Boolean
}