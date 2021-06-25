package com.example.grpc

import javax.persistence.Persistence

fun main() {
    val entityManagerFactory = Persistence.createEntityManagerFactory("demo")
    val entityManager = entityManagerFactory.createEntityManager()

    entityManager.close()
    entityManagerFactory.close()

}