package com.maelin.taskapp.domain.util

sealed class TaskOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): TaskOrder(orderType)
    class TimeCreated(orderType: OrderType): TaskOrder(orderType)
    class TimeDue(orderType: OrderType): TaskOrder(orderType)
}
