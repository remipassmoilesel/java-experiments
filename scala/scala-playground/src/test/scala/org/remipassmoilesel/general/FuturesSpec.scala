package org.remipassmoilesel.general

import org.remipassmoilesel.person.{Person, PersonDao}
import org.scalatest.{AsyncFunSpec, Matchers}

import scala.concurrent.Future

class FuturesSpec extends AsyncFunSpec with Matchers {

  val db = new PersonDao()

  describe("Futures and promises experiments") {

    describe("Futures experiments") {

      it("getNames() should return a list of persons") {
        val persons: List[Person] = db.getNames
        persons.size should equal(14)
      }

      it("getNamesAsyncFuture() should return a list of 3 persons") {
        val persons: Future[List[Person]] = db.getNamesAsyncFuture(3)
        persons.map(_.size should equal(3))
      }

      it("getNamesAsyncFuture() should throw if number of persons is too high") {
        recoverToSucceededIf[IllegalArgumentException] {
          db.getNamesAsyncFuture(25)
        }
      }

      it("getDoubledNamesAsyncFuture() should return a list of 20 persons") {
        val persons: Future[List[Person]] = db.getDoubledNamesAsyncFuture(10)
        persons.map(_.size should equal(20))
      }

    }

    describe("Promises experiments") {

      it("getNamesAsyncPromise() should return a list of 3 persons") {
        val persons: Future[List[Person]] = db.getNamesAsyncPromise(3)
        persons.map(_.size should equal(3))
      }

      it("getDoubledNamesAsyncPromiseWithError() should throw") {
        recoverToSucceededIf[IllegalArgumentException] {
          db.getDoubledNamesAsyncPromiseWithError(10)
        }
      }

    }
  }

}
