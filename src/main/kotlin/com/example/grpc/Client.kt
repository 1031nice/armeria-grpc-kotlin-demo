package com.example.grpc

import io.grpc.ManagedChannelBuilder

suspend fun main() {
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 8080)
        .usePlaintext()
        .build()

    val stub = RegionsServiceGrpc.newBlockingStub(channel)
    val response = stub.saveRegions(RegionsServiceOuterClass.SaveRegionsRequest.newBuilder()
        .setName("서울시")
        .setArea(RegionsServiceOuterClass.Area.newBuilder()
            .setX("111.11")
            .setY("33.33").build())
        .setArea(RegionsServiceOuterClass.Area.newBuilder()
            .setX("127.02")
            .setY("37.742").build()
        ).build()
    )

    println(response.stringId)
}
