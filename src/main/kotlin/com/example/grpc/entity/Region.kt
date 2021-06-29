package com.example.grpc.entity

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
open class Region (
    @Id
    @GeneratedValue
    @Column(name = "region_id")
    open var id: Integer? = null,
    open var name: String? = null,
    @ElementCollection
    open var area: MutableList<Point>? = null,
){
    constructor() : this(null, "")
}