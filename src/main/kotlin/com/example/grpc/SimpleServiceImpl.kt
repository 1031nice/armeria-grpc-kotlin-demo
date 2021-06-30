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
        // request의 정보로부터 Region 생성 후 데이터 바인딩
        // TODO saveAoi()와 중복
        val region = Region()
        val points = request.areaList
        val coordinates = mutableListOf<Coordinate>()
        for(point in points)
            coordinates.add(Coordinate(point.x.toDouble(), point.y.toDouble()))
        val factory = GeometryFactory()
        val polygon = factory.createPolygon(coordinates.toTypedArray())
        region.name = request.name
        region.area = polygon
        val regionId = areaRepository.save(region)

        return SimpleServiceOuterClass.SaveRegionsResponse.newBuilder()
            .setStringId(regionId.toString())
            .build()
    }

    override suspend fun saveAois(request: SimpleServiceOuterClass.SaveAoisRequest): SimpleServiceOuterClass.SaveAoisResponse {
        // request의 정보로부터 Aoi 생성 후 데이터 바인딩
        val aoi = Aoi()
        val points = request.areaList
        val coordinates = mutableListOf<Coordinate>()
        for(point in points)
            coordinates.add(Coordinate(point.x.toDouble(), point.y.toDouble()))
        val factory = GeometryFactory()
        val polygon = factory.createPolygon(coordinates.toTypedArray())
        aoi.name = request.name
        aoi.area = polygon
        val aoiId = areaRepository.save(aoi)

        return SimpleServiceOuterClass.SaveAoisResponse.newBuilder()
            .setStringId(aoiId.toString())
            .build()
    }

    // TODO SimpleServiceOuterClass.Aois 데이터 바인딩해서 리턴해야 한다
    // 이런 바인딩도 일일이 안하고 자동으로 할 방법이 있을텐데
    override suspend fun getAoisByRegionId(request: SimpleServiceOuterClass.GetAoisByRegionIdRequest): SimpleServiceOuterClass.GetAoisByRegionIdResponse {
        val list = areaRepository.getAoisIntersectWithRegion(request.regionId.toInt())
        val responseBuilder = SimpleServiceOuterClass.GetAoisByRegionIdResponse.newBuilder()
        for(aoi in list) {
            val aoiBuilder = SimpleServiceOuterClass.Aois.newBuilder()
            for(coordinate in aoi.area!!.coordinates) {
                val areaBuilder = SimpleServiceOuterClass.Area.newBuilder()
                aoiBuilder.addArea(areaBuilder.setX(coordinate.x.toString()).setY(coordinate.y.toString()).build())
            }
            responseBuilder.addAois(
                aoiBuilder.setAoisId(aoi.id.toString())
                    .setName(aoi.name)
                    .build()
            )
        }
        return responseBuilder.build()

//        return SimpleServiceOuterClass.GetAoisByRegionIdResponse.newBuilder()
//            .setAois(0,
//                SimpleServiceOuterClass.Aois.newBuilder()
//                    .setAoisId("1")
//                    .setName("dummy-aoi")
//                    .setArea(0, SimpleServiceOuterClass.Area.newBuilder().setX("1").setY("2").build())
//            ).build()
    }
}