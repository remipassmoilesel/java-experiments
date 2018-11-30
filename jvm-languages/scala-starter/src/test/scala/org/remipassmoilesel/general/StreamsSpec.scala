package org.remipassmoilesel.general

import org.scalatest._

import scala.collection.JavaConverters._

class StreamsSpec extends FunSpec with Matchers {

  describe("Stream experiments") {

    it("Should remove duplicate numbers") {
      val stringList: List[String] = List("1", "1", "2", "2", "3")
      stringList.toStream.distinct.size should equal(3)
    }

    it("Should convert list") {
      val scalaList = List("1", "1", "2", "2", "3")
      val javaList = scalaList.asJava

      javaList.size() should equal(scalaList.size)
    }

    it("Should filter list") {
      val scalaList = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
      val filtered = scalaList.filter(i => i % 2 == 0)
      val filteredShort = scalaList.filter(_ % 2 == 0)

      filtered should have size 5
      filtered should equal(filteredShort)
    }

  }

}


