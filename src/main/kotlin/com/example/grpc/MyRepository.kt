package com.example.grpc

import java.sql.*

/*
    겹치는 구역의 정보를 얻고 싶은 경우
    select name, area from region where ST_Intersects(area, 'SRID=4326;POLYGON((126.835 37.688,127.155 37.702,127.184 37.474,126.821 37.454,126.835 37.688))');

     전체 구역 중 겹치는 구역 t 겹치지 않는 구역 f로 모두 얻고 싶은 경우
     select ST_Intersects(area, 'SRID=4326;POLYGON((126.835 37.688,127.155 37.702,127.184 37.474,126.821 37.454,126.835 37.688))') from region;
*/

class MyRepository() {
    fun saveRegions() {

    }

    fun saveAois() {

    }

    fun getAoisByRegionId(regionId: String): List<SimpleServiceOuterClass.Aois> {
        // 1. regionId로부터 행정지역의 Polygon 정보를 얻어온다
        // 2. 저장된 모든 Aoi와 주어진 하나의 행정지역을 모두 비교한 뒤 intersects한 Aoi를 모두 저장하여 리턴한다

        var conn : Connection
        var stmt : Statement
        var rs : ResultSet
        var list = mutableListOf<SimpleServiceOuterClass.Aois>()


        val getRegionQuery = "select ST_AsText(area) as area, ST_SRID(area) as srid from region where region_id = $regionId;"

        val url = "jdbc:postgresql://192.168.99.100:5432/postgres"
        val user = "postgres"
        val password = "postgres"
        try {
            conn = DriverManager.getConnection(url, user, password)
            stmt = conn.createStatement()
            rs = stmt.executeQuery(getRegionQuery)
            var polygon = ""
            var srid = ""
            if(rs.next()) {
                polygon = rs.getString("area")
                srid = rs.getString("srid")
            }

            val getAoisByRegionIdQuery = "SELECT * FROM AOI WHERE ST_Intersects('SRID=$srid;$polygon', area)"
            rs = stmt.executeQuery(getAoisByRegionIdQuery)
            while(rs.next()) {
                // TODO 콘솔 출력이 아니라 area로 매핑해서 보내야 함
                println(rs.getString("aoi_id"))
                println(rs.getString("name"))
                println(rs.getString("area"))
            }

        } catch (e: SQLException) {
            println(e.message)
        }
        return list
    }
}