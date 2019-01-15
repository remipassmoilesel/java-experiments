package org.remipassmoilesel.general

import org.remipassmoilesel.person.PersonDao
import org.scalatest.{FunSpec, Matchers}

import scala.collection.mutable.ListBuffer

class CollectionsSpec extends FunSpec with Matchers {

  val db = new PersonDao()

  describe("Collections experiments") {

    it("Concatenate lists should work") {
      val list1 = List(1, 2, 3)
      val list2 = List(4, 5, 6)
      val list3 = list1 ++ list2  // same as :::, but allow to concatenate more types

      list3.size should equal(list1.size + list2.size)
    }

    it("Concatenate lists should work with deprecated operator") {
      val list1 = List(1, 2, 3)
      val list2 = List(4, 5, 6)
      val list3 = list1 ::: list2

      list3.size should equal(list1.size + list2.size)
    }

    it("Append element to mutable list should work") {
      var list1 = ListBuffer(1, 2, 3)
      list1 += 4

      list1.size should equal(4)
    }
  }

}
