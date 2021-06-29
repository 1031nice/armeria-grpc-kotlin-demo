package com.example.grpc.entity

import javax.persistence.Embeddable

@Embeddable
open class Point(
    open var x: Double,
    open var y: Double,
) {
    constructor() : this(0.0, 0.0)
}