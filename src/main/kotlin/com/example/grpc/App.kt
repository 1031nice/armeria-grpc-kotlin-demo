package com.example.grpc

import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.grpc.GrpcService

fun main(args: Array<String>) {
    val server = Server.builder()
        .http(8080)
        .service(GrpcService.builder()
            .addService(RegionsServiceImpl())
            .build())
        .build()

    server.start()
    println("Server started!")
}