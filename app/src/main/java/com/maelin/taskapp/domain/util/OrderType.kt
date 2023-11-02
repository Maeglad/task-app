package com.maelin.taskapp.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
