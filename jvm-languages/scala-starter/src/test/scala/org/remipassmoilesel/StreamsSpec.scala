package org.remipassmoilesel

import org.scalatest._
import scala.collection.JavaConverters._

class StreamsSpec extends FunSpec with Matchers {

  describe("Stream trials") {

    it("Should remove duplicate numbers") {
      val stringList: List[String] = List("1", "1", "2", "2", "3")
      stringList.toStream.distinct.size should equal(3)
    }

    it("Should convert list") {
      val scalaList = List("1", "1", "2", "2", "3")
      val javaList = scalaList.asJava

      javaList.size() should equal(scalaList.size)
    }

  }

}


