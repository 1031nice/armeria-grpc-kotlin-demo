package com.example.grpc

import com.example.grpc.entity.Aoi
import com.example.grpc.entity.Area
import com.example.grpc.entity.Region
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import java.io.Serializable

class AreaRepository<E: Area> {

    private val registry = StandardServiceRegistryBuilder().configure().build()
    private val sessionFactory = MetadataSources(registry).buildMetadata().buildSessionFactory()

    fun save(e: E): Serializable {
        try {
            val session = sessionFactory.openSession()
            val tx = session.transaction
            tx.begin()
            val saveId = session.save(e)
            tx.commit()
            return saveId
        }
        // TODO 예외처리
        catch(e: Exception) { println(e.message) }
        return -1
    }

    // TODO 제네릭으로 분명 될텐데
    fun findAoi(id: Serializable): Any? {
        val session = sessionFactory.openSession()
        return session.find(Aoi::class.java, id)
    }

    fun findRegion(id: Serializable): Any? {
        val session = sessionFactory.openSession()
        return session.find(Region::class.java, id)
    }

    fun getAoisIntersectWithRegion(regionId: Int): List<Aoi> {
        try {
            var ret = mutableListOf<Aoi>()
            val session = sessionFactory.openSession()
            val region = session.find(Region::class.java, regionId)
            val sqlString = "SELECT * FROM AOI WHERE ST_Intersects('SRID=${region.area!!.srid};${region.area.toString()}', area)"
            val query = session.createNativeQuery(sqlString, Aoi::class.java)
            for (any in query.resultList)
                ret.add(any as Aoi)
            return ret
        }
        // TODO 예외처리
        catch(e: Exception) { println(e.message) }
        return listOf()
    }
}