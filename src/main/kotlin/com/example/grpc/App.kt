package com.example.grpc

import com.example.grpc.entity.Area
import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.grpc.GrpcService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module

val simpleModule = module {
    single { AreaRepository<Area>() }
}

class App: KoinComponent {
    val areaRepository: AreaRepository<Area> by inject()
    fun start() = run {
        Server.builder().apply {
            http(8080)
                .service(
                    GrpcService.builder()
                        // 이걸 추가해야 debug form이 생기는구나!
                        .enableUnframedRequests(true)
                        .addService(SimpleServiceImpl(areaRepository))
                        .build()
                )
                .serviceUnder("/docs", DocService())
        }.build().start()
        println("Server started!")
    }
}

fun main() {
    startKoin { modules(simpleModule) }

    val app = App()
    app.start()
}