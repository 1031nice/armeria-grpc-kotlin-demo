package com.example.grpc

import io.grpc.ManagedChannelBuilder

suspend fun main() {
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 8080)
        .usePlaintext()
        .build()

//    val stub = SimpleServiceGrpc.newBlockingStub(channel)
    val stub = SimpleServiceGrpcKt.SimpleServiceCoroutineStub(channel)

    val area : MutableList<SimpleServiceOuterClass.Area> = mutableListOf()
    area.add(SimpleServiceOuterClass.Area.newBuilder()
        .setX("37.517386")
        .setY("127.112990").build())
    area.add(SimpleServiceOuterClass.Area.newBuilder()
        .setX("37.511687")
        .setY("127.128048").build())
    area.add(SimpleServiceOuterClass.Area.newBuilder()
        .setX("37.521286")
        .setY("127.133858").build())
    area.add(SimpleServiceOuterClass.Area.newBuilder()
        .setX("37.527938")
        .setY("127.119110").build())
    area.add(SimpleServiceOuterClass.Area.newBuilder()
        .setX("37.517386")
        .setY("127.112990").build())


    val response = stub.saveRegions(SimpleServiceOuterClass.SaveRegionsRequest.newBuilder()
        .setName("서울시")
        .addAllArea(area).build())

//    val response = stub.getAoisByRegionId(
//        SimpleServiceOuterClass.GetAoisByRegionIdRequest.newBuilder()
//            .setRegionId("1").build()
//    )

    println(response)
}
