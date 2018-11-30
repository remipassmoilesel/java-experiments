package org.remipassmoilesel.general

import org.scalatest._

/**
  * Scalatest offre plusieurs styles de classes de test.
  * - FunSuite ressemble à JUnit,
  * - FunSpec ressemble à Mocha avec describe() et it(),
  *
  * http://www.scalatest.org/user_guide/selecting_a_style
  */

class FunSuiteSpec extends FunSuite {

  test("Test description") {
    assert(true)
  }

}

class FunSpecSpec extends FunSpec {

  describe("Test group description") {
    it("Test description") {
      assert(true)
    }
  }

}


