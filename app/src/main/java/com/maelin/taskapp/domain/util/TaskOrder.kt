package com.maelin.taskapp.domain.util

sealed class TaskOrder(val orderType: OrderType) {
    class Name(orderType: OrderType): TaskOrder(orderType)
    class TimeCreated(orderType: OrderType): TaskOrder(orderType)
    class TimeDue(orderType: OrderType): TaskOrder(orderType)

    fun copy(orderType: OrderType): TaskOrder {
        return when (this) {
            is Name -> Name(orderType)
            is TimeCreated -> TimeCreated(orderType)
            is TimeDue -> TimeDue(orderType)
        }
    }
}
