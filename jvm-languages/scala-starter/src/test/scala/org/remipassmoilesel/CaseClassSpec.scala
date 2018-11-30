package org.remipassmoilesel

import java.util.UUID

import org.scalatest.{FunSpec, Matchers}

case class SimpleCaseClass(prop1: String, prop2: String) {
  val id = UUID.randomUUID().toString
}

case class CaseClassWithUuid(prop1: String, prop2: String, uuid: String) {
}

object CaseClassWithUuid {
  def apply(prop1: String, prop2: String): CaseClassWithUuid = CaseClassWithUuid(prop1, prop2, UUID.randomUUID().toString)
}

class CaseClassSpec extends FunSpec with Matchers {

  describe("Case class experiments") {

    it("Equality experiments on case class") {
      val person1 = SimpleCaseClass("foo", "bar")
      val person2 = SimpleCaseClass("foo", "bar")
      val person3 = SimpleCaseClass("bar", "foo")

      person1 should equal(person1)
      person1 should equal(person2)
      person1 should not equal (person3)

      person1 == person1 should be(true)
      person1 == person2 should be(true)
      person1 == person3 should be(false)
    }

    it("Equality experiments with generated uuid field") {
      val person1 = CaseClassWithUuid("foo", "bar")
      val person2 = CaseClassWithUuid("foo", "bar")
      val person3 = CaseClassWithUuid("bar", "foo")

      person1 should equal(person1)
      person1 should not equal(person2)
      person1 should not equal (person3)

      person1 == person1 should be(true)
      person1 == person2 should be(false)
      person1 == person3 should be(false)
    }

  }

}
