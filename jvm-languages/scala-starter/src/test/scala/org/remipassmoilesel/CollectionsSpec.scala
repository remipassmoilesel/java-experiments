package org.remipassmoilesel

import org.remipassmoilesel.person.PersonDao
import org.scalatest.{AsyncFunSpec, Matchers}

class CollectionsSpec extends AsyncFunSpec with Matchers {

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
  }

}
