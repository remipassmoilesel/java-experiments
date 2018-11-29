package org.remipassmoilesel

import org.scalatest._

/**
  * Matchers:
  *
  * Pour utiliser les matchers, ajotuer le trait Matchers
  *
  * http://www.scalatest.org/user_guide/using_matchers
  *
  */
class MatchersTrialsSpec extends FunSpec with Matchers {

  describe("Equality") {

    it("Should be equal") {
      "a string" should equal ("a string")
    }

  }

}


