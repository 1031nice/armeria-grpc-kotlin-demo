package com.example.grpc

import io.grpc.ManagedChannelBuilder

suspend fun main() {
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 8080)
        .usePlaintext()
        .build()

    val stub = GreetingServiceGrpc.newBlockingStub(channel)
    val response = stub.greeting(GreetingServiceOuterClass.HelloRequest.newBuilder().setName("Donghun").build())

    println(response.greeting)
}
