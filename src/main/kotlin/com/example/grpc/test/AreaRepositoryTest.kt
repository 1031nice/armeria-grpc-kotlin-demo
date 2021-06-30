package com.example.grpc.test

import com.example.grpc.AreaRepository
import com.example.grpc.entity.Aoi
import com.example.grpc.entity.Area
import com.example.grpc.entity.Region
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory

fun main() {
    val repo = AreaRepository<Area>()

    // save test
    println("*****save region test*****")
    val aoi = Aoi()
    aoi.name = "한라산"
    val coordinates = arrayOf(
        Coordinate(37.517386, 127.112990),
        Coordinate(37.511687, 127.128048),
        Coordinate(37.521286, 127.133858),
        Coordinate(37.527938, 127.119110),
        Coordinate(37.517386, 127.112990)
    )
    val factory = GeometryFactory()
    var polygon = factory.createPolygon(coordinates)
    aoi.area = polygon
    val aoiId = repo.save(aoi)
    println(aoiId)
    println(aoi.area)
    println(aoi.name)
    println()

    println("*****save aoi test*****")
    val region = Region()
    region.name = "한라산"
    polygon = factory.createPolygon(coordinates)
    region.area = polygon
    val regionId = repo.save(aoi)
    println(regionId)
    println(region.area)
    println(region.name)
    println()

    println("*****intersects test*****")
    for (aoi in repo.getAoisIntersectWithRegion(1)) {
        println(aoi.name)
        println(aoi.area)
        println("-----------")
    }
    println()
}