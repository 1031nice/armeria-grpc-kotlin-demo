package com.example.grpc

import java.sql.*

fun main() {
    val conn : Connection
    val stmt : Statement
    val rs : ResultSet

    val url = "jdbc:postgresql://192.168.99.100:5432/postgres"
    val user = "postgres"
    val password = "postgres"

    try {
        conn = DriverManager.getConnection(url, user, password)
        stmt = conn.createStatement()
        rs = stmt.executeQuery("SELECT * FROM REGION")

        while(rs.next()) {
            println(rs.getString("name"))
            println(rs.getString("area"))
        }
    } catch (e: SQLException) {
        println(e.message)
    }
}