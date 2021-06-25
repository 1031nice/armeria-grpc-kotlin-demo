package com.example.grpc

class SimpleServiceImpl : SimpleServiceGrpcKt.SimpleServiceCoroutineImplBase() {
    override suspend fun saveRegions(request: SimpleServiceOuterClass.SaveRegionsRequest): SimpleServiceOuterClass.SaveRegionsResponse {
        println(request)
        return SimpleServiceOuterClass.SaveRegionsResponse.newBuilder()
            .setStringId("1") // TODO 데이터베이스에 저장 후 저장된 아이디 리턴
            .build()
    }

    override suspend fun saveAois(request: SimpleServiceOuterClass.SaveAoisRequest): SimpleServiceOuterClass.SaveAoisResponse {
        return SimpleServiceOuterClass.SaveAoisResponse.newBuilder()
            .setStringId("1") // TODO 데이터베이스에 저장 후 저장된 아이디 리턴
            .build()
    }

    override suspend fun getAoisByRegionId(request: SimpleServiceOuterClass.GetAoisByRegionIdRequest): SimpleServiceOuterClass.GetAoisByRegionIdResponse {
        // TODO 데이터베이스에서 해당 행정지역에 Aois가 있는지 검사
        return SimpleServiceOuterClass.GetAoisByRegionIdResponse.newBuilder()
            .setAois(0,
                SimpleServiceOuterClass.Aois.newBuilder()
                    .setAoisId("1")
                    .setName("Aois1")
                    .setArea(0, SimpleServiceOuterClass.Area.newBuilder().setX("1").setY("2").build())
            ).build()
    }
}