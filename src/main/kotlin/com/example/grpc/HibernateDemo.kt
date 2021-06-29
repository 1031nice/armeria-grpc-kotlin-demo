package com.example.grpc

import com.example.grpc.entity.Aoi
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory

/*
create table aoi( id uuid DEFAULT uuid_generate_v4 (), name text, area geometry('Polygon', 4326));
insert into Aoi (area, name, aoi_id) values (st_geomfromtext('POLYGON ((37.517386 127.11299, 37.511687 127.128048, 37.521286 127.133858, 37.527938 127.11911, 37.517386 127.11299))', 4326), '한라산', 3);
insert into Aoi (area, name) values (st_geomfromtext('POLYGON ((37.517386 127.11299, 37.511687 127.128048, 37.521286 127.133858, 37.527938 127.11911, 37.517386 127.11299))', 4326), '한라산');
 */

fun main() {

    try {
        val registry = StandardServiceRegistryBuilder().configure().build()
        val sessionFactory = MetadataSources(registry).buildMetadata().buildSessionFactory()
        val session = sessionFactory.openSession()
        val tx = session.transaction
        tx.begin()

        // select
        // em의 find를 이용하여 찾고 싶으나 deserialize exception이 발생하는데 이걸 해결하지 못하여 일단 이것도 쿼리 직접 작성
//        val find = session.get(Aoi::class.java, 1)
//        println(find)
//        val resultList = session.createQuery("select a from Aoi a", Aoi::class.java).resultList
//        println(resultList)
//        val query = session.createNativeQuery("select st_asText(area), name, cast(aoi_id as varchar) from aoi")
//        for (any in query.resultList) {
//            println(any)
//            val any = any as Array<*>
//            for(i in any)
//                println(i)
//        }

        // polygon 데이터 만들기
        val aoi = Aoi()
        aoi.name = "한라산"
        val coordinates = arrayOf(
            Coordinate(37.517386, 127.112990),
            Coordinate(37.511687, 127.128048),
            Coordinate(37.521286, 127.133858),
            Coordinate(37.527938, 127.119110),
            Coordinate(37.517386, 127.112990)
        )
        val factory = GeometryFactory()
        val polygon = factory.createPolygon(coordinates)
        aoi.area = polygon

        // insert
//        val query2 = session.createNativeQuery("insert into Aoi (area, name, aoi_id) values (st_geomfromtext(" + "'" + aoi.area + "', 4326), '" + aoi.name + "', 1)")
        val query2 = session.createNativeQuery("insert into ${aoi.javaClass.simpleName} (area, name, id) values (st_geomfromtext(" + "'" + aoi.area + "', 4326), '" + aoi.name + "', nextval('id_sequence'))")
        query2.executeUpdate()
//        session.persist(aoi)

        tx.commit()
    }
    catch(e : Exception) {
        e.printStackTrace()
        println(e.message)
    }
}