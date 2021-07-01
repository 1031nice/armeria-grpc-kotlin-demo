package com.example.grpc.converter

import com.example.grpc.SimpleServiceOuterClass
import com.example.grpc.entity.Aoi
import com.example.grpc.entity.Region
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory

// TODO GeometryFactory도 싱글톤이면 좋을 것 같다
fun SimpleServiceOuterClass.SaveAoisRequest.toEntity() : Aoi {
    val aoi = Aoi()
    val coordinates = mutableListOf<Coordinate>()
    for(point in areaList)
        coordinates.add(Coordinate(point.x.toDouble(), point.y.toDouble()))
    aoi.name = name
    aoi.area = GeometryFactory().createPolygon(coordinates.toTypedArray())
    return aoi
}

// TODO 위 코드와 똑같으므로 중복을 제거할 수 있을 것 같다
fun SimpleServiceOuterClass.SaveRegionsRequest.toEntity() : Region {
    val region = Region()
    val coordinates = mutableListOf<Coordinate>()
    for(point in areaList)
        coordinates.add(Coordinate(point.x.toDouble(), point.y.toDouble()))
    region.name = name
    region.area = GeometryFactory().createPolygon(coordinates.toTypedArray())
    return region
}