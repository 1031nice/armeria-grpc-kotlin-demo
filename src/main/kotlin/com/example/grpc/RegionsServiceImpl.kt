package com.example.grpc

import com.example.grpc.RegionsServiceOuterClass.*

class RegionsServiceImpl : RegionsServiceGrpcKt.RegionsServiceCoroutineImplBase() {
    override suspend fun saveRegions(request: SaveRegionsRequest): SaveRegionsResponse {
        println(request)
        // TODO 데이터베이스에 저장한 뒤 저장된 id를 리턴
        return RegionsServiceOuterClass.SaveRegionsResponse.newBuilder()
            .setStringId("1")
            .build()
    }
}