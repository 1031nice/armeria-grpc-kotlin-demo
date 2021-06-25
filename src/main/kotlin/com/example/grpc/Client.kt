package com.example.grpc

import io.grpc.ManagedChannelBuilder

suspend fun main() {
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 8080)
        .usePlaintext()
        .build()

    val stub = SimpleServiceGrpc.newBlockingStub(channel)
    val area : MutableList<SimpleServiceOuterClass.Area> = mutableListOf()
    area.add(SimpleServiceOuterClass.Area.newBuilder()
        .setX("111.11")
        .setY("33.33").build())
    area.add(SimpleServiceOuterClass.Area.newBuilder()
        .setX("127.02")
        .setY("37.742").build())

    val response = stub.saveRegions(SimpleServiceOuterClass.SaveRegionsRequest.newBuilder()
        .setName("서울시")
        .addAllArea(area).build())

    println(response.stringId)
}
