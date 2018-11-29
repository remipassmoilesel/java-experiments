package org.remipassmoilesel

import org.scalatest._

import scala.collection.JavaConverters._

class ControlsSpec extends FunSpec with Matchers {

  describe("For experiments") {

    it("Should create a correct list") {
      val list = for (i <- 1 to 2; j <- 1 to 2) yield i + " " + j
      list should have size 4
      list should equal(Vector("1 1", "1 2", "2 1", "2 2"))
    }

  }

}


