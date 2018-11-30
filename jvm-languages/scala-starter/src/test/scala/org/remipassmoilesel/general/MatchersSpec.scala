package org.remipassmoilesel.general

import org.scalatest._

/**
  * Matchers:
  *
  * Pour utiliser les matchers, ajotuer le trait Matchers
  *
  * http://www.scalatest.org/user_guide/using_matchers
  *
  */
class MatchersSpec extends FunSpec with Matchers {

  describe("Matchers experiments") {

    it("Should be equal") {
      "a string" should equal ("a string")
    }

    it("Should have correct size") {
      1 to 3 should have size 3
    }

  }

}


