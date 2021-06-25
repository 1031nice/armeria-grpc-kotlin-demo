package com.example.grpc

class GreetingServiceImpl : GreetingServiceGrpcKt.GreetingServiceCoroutineImplBase() {

    override suspend fun greeting(request: GreetingServiceOuterClass.HelloRequest): GreetingServiceOuterClass.HelloResponse {
        println(request)
        return GreetingServiceOuterClass.HelloResponse.newBuilder()
            .setGreeting("Hello, " + request.name + "!")
            .build()
    }
}