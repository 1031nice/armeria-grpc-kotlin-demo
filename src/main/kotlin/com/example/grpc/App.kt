package com.example.grpc

import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.grpc.GrpcService

/*
    protocol buffer message의 필드 타입은 몽땅 string으로 하면 어떤 문제가 있을까
    repeated field는 어떻게 주입하는걸까
 */

fun main(args: Array<String>) {
    val server = Server.builder()
        .http(8080)
        .service(
            GrpcService.builder()
                .addService(SimpleServiceImpl())
                .build()
        )
        .serviceUnder("/docs", DocService())
        .build()

    server.start()
    println("Server started!")
}