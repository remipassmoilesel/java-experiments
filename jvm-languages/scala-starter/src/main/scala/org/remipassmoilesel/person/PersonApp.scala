package org.remipassmoilesel.person

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object PersonApp {
  val dao = new PersonDao

  def run() = {
    askPersons
    askPersonsWithError
  }

  private def askPersons() = {
    dao.getNamesAsyncFuture(3) onComplete {
      case Success(persons) => println(s"Persons: $persons")
      case Failure(t) => println("An error has occurred: " + t.getMessage)
    }
  }

  private def askPersonsWithError() = {
    dao.getNamesAsyncFuture(21) onComplete {
      case Success(persons) => println(s"Persons: $persons")
      case Failure(t) => println("An error has occurred: " + t.getMessage)
    }
  }

}
