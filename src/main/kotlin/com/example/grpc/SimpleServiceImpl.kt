package com.example.grpc

import com.example.grpc.entity.Aoi
import com.example.grpc.entity.Area
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory

class SimpleServiceImpl : SimpleServiceGrpcKt.SimpleServiceCoroutineImplBase() {
    override suspend fun saveRegions(request: SimpleServiceOuterClass.SaveRegionsRequest): SimpleServiceOuterClass.SaveRegionsResponse {
        return SimpleServiceOuterClass.SaveRegionsResponse.newBuilder()
            .setStringId("1") // TODO 데이터베이스에 저장 후 저장된 아이디 리턴
            .build()
    }

    override suspend fun saveAois(request: SimpleServiceOuterClass.SaveAoisRequest): SimpleServiceOuterClass.SaveAoisResponse {
        val aoi = Aoi()
        aoi.name = request.name
        val points = request.areaList
        val coordinates = mutableListOf<Coordinate>()
        for(point in points)
            coordinates.add(Coordinate(point.x.toDouble(), point.y.toDouble()))
        val factory = GeometryFactory()
        val polygon = factory.createPolygon(coordinates.toTypedArray())
        aoi.area = polygon.toString() // TODO 이거 되나

        println("debug: ${aoi.name}")
        println("debug: ${aoi.area}")

        return SimpleServiceOuterClass.SaveAoisResponse.newBuilder()
            .setStringId("987") // TODO 데이터베이스에 저장 후 저장된 아이디 리턴
            .build()
    }

    override suspend fun getAoisByRegionId(request: SimpleServiceOuterClass.GetAoisByRegionIdRequest): SimpleServiceOuterClass.GetAoisByRegionIdResponse {
        // TODO 데이터베이스에서 해당 행정지역에 Aois가 있는지 검사
        val repository = AreaRepository<Area>()

        val list = repository.getAoisIntersectWithRegion(Integer(request.regionId.toInt()))
        for(i in list) {
            println(i.name)
        }

        return SimpleServiceOuterClass.GetAoisByRegionIdResponse.newBuilder()
            .setAois(0,
                SimpleServiceOuterClass.Aois.newBuilder()
                    .setAoisId("1")
                    .setName("Aois1")
                    .setArea(0, SimpleServiceOuterClass.Area.newBuilder().setX("1").setY("2").build())
            ).build()
    }
}