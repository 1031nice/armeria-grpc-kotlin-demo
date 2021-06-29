package com.example.grpc

import com.example.grpc.entity.Aoi
import com.example.grpc.entity.Area
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder

/*
    겹치는 구역의 정보를 얻고 싶은 경우
    select name, area from region where ST_Intersects(area, 'SRID=4326;POLYGON((126.835 37.688,127.155 37.702,127.184 37.474,126.821 37.454,126.835 37.688))');

     전체 구역 중 겹치는 구역 t 겹치지 않는 구역 f로 모두 얻고 싶은 경우
     select ST_Intersects(area, 'SRID=4326;POLYGON((126.835 37.688,127.155 37.702,127.184 37.474,126.821 37.454,126.835 37.688))') from region;
*/

class AreaRepository<E: Area> {

    fun save(e: E) {
        try {
            // Aoi에 name, area(polygon data)가 잘 들어온다고 가정
            // TODO SessionFactory singleton으로 만들어서 애플리케이션 내에서 한 번만 생성하기
            // Session은 EntityManager 자식이니까 transaction당 하나만 만들어지겠지? 싱글톤으로 만들면 안되겠지?
            val registry = StandardServiceRegistryBuilder().configure().build()
            val sessionFactory = MetadataSources(registry).buildMetadata().buildSessionFactory()
            val session = sessionFactory.openSession()
            val tx = session.transaction
            tx.begin()

            val sqlString = "insert into ${e.javaClass.simpleName} (area, name, id) values (st_geomfromtext(" + "'" + e.area + "', 4326), '" + e.name + "', nextval('id_sequence')"
            val query = session.createNativeQuery(sqlString)
            query.executeUpdate()
            tx.commit()
        }
        // TODO 예외처리
        catch(e: Exception) { println(e.message) }
    }

    fun getAoisIntersectWithRegion(regionId: Integer): List<Aoi> {
        // 1. regionId로부터 행정지역의 Polygon 정보를 얻어온다
        // 2. 저장된 모든 Aoi와 주어진 하나의 행정지역을 모두 비교한 뒤 intersects한 Aoi를 모두 저장하여 리턴한다

        try {
            // Aoi에 name, area(polygon data)가 잘 들어온다고 가정
            // TODO SessionFactory singleton으로 만들어서 애플리케이션 내에서 한 번만 생성하기
            // Session은 EntityManager 자식이니까 transaction당 하나만 만들어지겠지? 싱글톤으로 만들면 안되겠지?
            var ret = mutableListOf<Aoi>()
            val registry = StandardServiceRegistryBuilder().configure().build()
            val sessionFactory = MetadataSources(registry).buildMetadata().buildSessionFactory()
            val session = sessionFactory.openSession()

            var sqlString = "select ST_AsText(area) as area, ST_SRID(area) as srid from region where id = $regionId;"
            var query = session.createNativeQuery(sqlString)
            var result = query.resultList.first() as Array<*>
            val polygon: String = result[0] as String
            val srid = result[1]

            sqlString = "SELECT name, ST_AsText(area) as area, id FROM AOI WHERE ST_Intersects('SRID=$srid;$polygon', area)"
            query = session.createNativeQuery(sqlString)
            for (any in query.resultList) {
                val any = any as Array<*>
                var aoi = Aoi()
                aoi.name = any[0] as String?
                aoi.area = any[1] as String?
                ret.add(aoi)
            }
            return ret
        }
        // TODO 예외처리
        catch(e: Exception) { println(e.message) }
        return listOf()
    }
}