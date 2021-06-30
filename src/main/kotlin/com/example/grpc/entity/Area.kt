package com.example.grpc.entity

import org.locationtech.jts.geom.Polygon
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class Area (
    @Id
    @GeneratedValue
    open var id: Int? = null,
    @Column
    open var name: String? = null,
    @Column
    open var area: Polygon? = null, // convert string(Polygon) to Polygon 방법을 못찾아서 일단 String
) {
    constructor() : this(null, "", null)
}