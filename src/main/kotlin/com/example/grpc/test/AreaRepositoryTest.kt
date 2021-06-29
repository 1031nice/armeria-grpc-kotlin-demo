package com.example.grpc.test

import com.example.grpc.AreaRepository
import com.example.grpc.entity.Aoi
import com.example.grpc.entity.Area
import com.example.grpc.entity.Region
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory

/*

create table aoi( id uuid DEFAULT uuid_generate_v4 (), name text, area geometry('Polygon', 4326));
insert into Aoi (area, name, aoi_id) values (st_geomfromtext('POLYGON ((37.517386 127.11299, 37.511687 127.128048, 37.521286 127.133858, 37.527938 127.11911, 37.517386 127.11299))', 4326), '한라산', 3);
insert into Aoi (area, name) values (st_geomfromtext('POLYGON ((37.517386 127.11299, 37.511687 127.128048, 37.521286 127.133858, 37.527938 127.11911, 37.517386 127.11299))', 4326), '한라산');
 INSERT INTO region VALUES ('서울시', st_geomfromtext('Polygon((126.835 37.688, 127.155 37.702, 127.184 37.474, 126.821 37.454, 126.835 37.688))', 4326), 1);
INSERT INTO region VALUES ('과천시', st_geomfromtext('Polygon((126.998 37.463, 127.028 37.436, 126.992 37.403, 126.965 37.421, 126.998 37.463))', 4326), 2);
INSERT INTO region VALUES ('의정부시', st_geomfromtext('Polygon((127.025 37.766, 127.096 37.766, 127.099 37.724, 127.035 37.705, 127.020 37.742, 127.025 37.766))', 4326), 3);


INSERT INTO aoi VALUES ('북한산', st_geomfromtext('Polygon((127.02 37.742, 127.023 37.664, 126.945 37.605, 126.962 37.692, 127.02 37.742))', 4326), 1), ('관악산', st_geomfromtext('Polygon((126.985 37.464, 126.980 37.429, 126.933 37.406, 126.910 37.432, 126.931 37.456, 126.985 37.464))', 4326), 2);

SELECT * FROM AOI WHERE ST_Intersects('SRID=4326;POLYGON((126.998 37.463,127.028 37.436,126.992 37.403,126.965 37.421,126.998 37.463))', area)
 */

fun main() {
    val repo = AreaRepository<Area>()

    // save test
    println("*****save region test*****")
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
    var polygon = factory.createPolygon(coordinates)
    aoi.area = polygon.toString()
    val aoiId = repo.save(aoi)
    println(aoiId)
    println(aoi.area)
    println(aoi.name)
    println()

    println("*****save aoi test*****")
    val region = Region()
    region.name = "한라산"
    polygon = factory.createPolygon(coordinates)
    aoi.area = polygon.toString()
    val regionId = repo.save(aoi)
    println(regionId)
    println(region.area)
    println(region.name)
    println()

    println("*****intersects test*****")
    for (aoi in repo.getAoisIntersectWithRegion(1)) {
        println(aoi.name)
        println(aoi.area)
        println("-----------")
    }
    println()
}