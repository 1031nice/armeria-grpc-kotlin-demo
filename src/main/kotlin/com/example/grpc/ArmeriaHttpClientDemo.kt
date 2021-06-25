package com.example.grpc

import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.annotation.Get
import com.linecorp.armeria.server.annotation.Param
import com.linecorp.armeria.server.annotation.Post

// TODO 응답을 JSON 형식으로 변환
class ArmeriaHttpClientDemo {
    // 클라이언트는 nested인 경우 어떻게 던지지?
    // 백에서는 그걸 binding해서 하나로 받을 수 있나?
    @Post("/regions")
    suspend fun test1(@Param("name") name: String,
                      @Param("x") x: String,
                      @Param("y") y: String): String {
        return "name: $name, x: $x, y: $y"
    }

    @Post("/aois")
    suspend fun test2(@Param("name") name: String,
                      @Param("area") area: String): String {
        return "name: $name, area: $area"
    }

    @Get("/regions/{regionId}/aois/intersects")
    suspend fun test3(@Param("regionId") regionId: String): String {
        return regionId
    }
}

fun main() {
    val server = Server.builder()
        .http(8080)
        .annotatedService()
        .build(ArmeriaHttpClientDemo())
        .build()
    server.start()
    println("server started!")
}