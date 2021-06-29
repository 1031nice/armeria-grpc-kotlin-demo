package com.example.grpc.entity

import org.locationtech.jts.geom.Polygon
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class Area (
    @Id
    open var id: Integer? = null,
    @Column
    open var name: String? = null,
    @Column
    open var area: Polygon? = null,
) {
    constructor() : this(null, "", null)
}