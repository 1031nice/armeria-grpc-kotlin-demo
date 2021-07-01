package com.example.grpc

import com.example.grpc.converter.toEntity
import com.example.grpc.entity.Area

class SimpleServiceImpl(

    val areaRepository: AreaRepository<Area>

) : SimpleServiceGrpcKt.SimpleServiceCoroutineImplBase() {
    override suspend fun saveRegions(request: SimpleServiceOuterClass.SaveRegionsRequest): SimpleServiceOuterClass.SaveRegionsResponse {
        val region = request.toEntity()
        val regionId = areaRepository.save(region)

        return SimpleServiceOuterClass.SaveRegionsResponse.newBuilder()
            .setStringId(regionId.toString())
            .build()
    }

    override suspend fun saveAois(request: SimpleServiceOuterClass.SaveAoisRequest): SimpleServiceOuterClass.SaveAoisResponse {
        val aoi = request.toEntity()
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
    }
}