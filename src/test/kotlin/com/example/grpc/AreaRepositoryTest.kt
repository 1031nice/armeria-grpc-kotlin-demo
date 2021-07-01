package com.example.grpc

import com.example.grpc.entity.Aoi
import com.example.grpc.entity.Area
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory

internal class AreaRepositoryTest : FunSpec() {

    init {

        test("save") {
            // When Aoi가 DB에 저장이 되면
            val areaRepository = AreaRepository<Area>() // Mock 객체로 바꿔야할 것 같다
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
            aoi.area = polygon
            val aoiId = areaRepository.save(aoi)

            // Then 같은 id로 DB에서 불러온 것과 동등해야 한다
            val find = areaRepository.findAoi(aoiId)
            aoi shouldBeEqualToComparingFields find as Any
        }

        test("get AOIs by region id(intersects)") {
//            val areaRepository = AreaRepository<Area>()
//            val aois = areaRepository.getAoisIntersectWithRegion(1)
            println("아직 작성되지 않은 테스트")
        }

    }

}