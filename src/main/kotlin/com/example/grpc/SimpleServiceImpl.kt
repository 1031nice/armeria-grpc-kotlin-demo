package com.example.grpc

import com.example.grpc.entity.Aoi
import com.example.grpc.entity.Area
import com.example.grpc.entity.Region
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory

class SimpleServiceImpl(

    val areaRepository: AreaRepository<Area>

) : SimpleServiceGrpcKt.SimpleServiceCoroutineImplBase() {
    override suspend fun saveRegions(request: SimpleServiceOuterClass.SaveRegionsRequest): SimpleServiceOuterClass.SaveRegionsResponse {
        val region = Region()
        region.name = request.name
        val points = request.areaList
        val coordinates = mutableListOf<Coordinate>()
        for(point in points)
            coordinates.add(Coordinate(point.x.toDouble(), point.y.toDouble()))
        val factory = GeometryFactory()
        val polygon = factory.createPolygon(coordinates.toTypedArray())
        region.area = polygon.toString() // 이거 되나

        println("debug: ${region.name}")
        println("debug: ${region.area}")

        val regionId = areaRepository.save(region)

        return SimpleServiceOuterClass.SaveRegionsResponse.newBuilder()
            .setStringId(regionId.toString())
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
        aoi.area = polygon.toString() // 이거 되나

        println("debug: ${aoi.name}")
        println("debug: ${aoi.area}")

        val aoiId = areaRepository.save(aoi)

        return SimpleServiceOuterClass.SaveAoisResponse.newBuilder()
            .setStringId(aoiId.toString())
            .build()
    }

    // TODO SimpleServiceOuterClass.Aois 데이터 바인딩해서 리턴해야 한다
    // 그런데 Polygon 꼴의 string을 Polygon으로 변환하는 방법을 찾지 못하면
    // Polygon으로부터 Point 정보를 얻지 못하고 그럼 각각의 좌표를 Aoi에 바인딩 못할 것 같다
    // Polygon을 Point 리스트로 변환하는 게 분명 있을텐데
    // 그리고 이런 바인딩도 일일이 안하고 자동으로 할 방법이 있을텐데
    override suspend fun getAoisByRegionId(request: SimpleServiceOuterClass.GetAoisByRegionIdRequest): SimpleServiceOuterClass.GetAoisByRegionIdResponse {
        val list = areaRepository.getAoisIntersectWithRegion(request.regionId.toInt())
        for(i in list) {
            println(i.name)
        }
        return SimpleServiceOuterClass.GetAoisByRegionIdResponse.newBuilder()
            .setAois(0,
                SimpleServiceOuterClass.Aois.newBuilder()
                    .setAoisId("1")
                    .setName("dummy-aoi")
                    .setArea(0, SimpleServiceOuterClass.Area.newBuilder().setX("1").setY("2").build())
            ).build()
    }
}