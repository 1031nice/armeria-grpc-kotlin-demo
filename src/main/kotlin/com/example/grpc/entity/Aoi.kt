package com.example.grpc.entity

import org.locationtech.jts.geom.Polygon
import java.io.Serializable
import javax.persistence.*

@Entity
@Table
open class Aoi (
    @Id
    @GeneratedValue
    @Column(name = "aoi_id")
    open var id: Integer? = null,
    @Column(name = "name")
    open var name: String? = null,
//    @ElementCollection
//    open var area: MutableList<Point>? = null,
    @Column(name = "area")
    open var area: Polygon? = null,
) {

    constructor() : this(null, "", null)

}