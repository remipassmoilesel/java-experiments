package org.remipassmoilesel.person

import java.util.concurrent.Executors

import org.remipassmoilesel.Utils

import scala.concurrent.{ExecutionContext, Future, Promise}

class PersonDao {

  implicit val execContext = instantiateDedicatedExecutionContext()

  def getNames: List[Person] = {
    Utils.randomSleep()
    List(
      Person("Christeen", "Fairfax"),
      Person("Albertha", "Slade"),
      Person("Libby", "Niehaus"),
      Person("Minna", "Baine"),
      Person("Shaunta", "Lamarr"),
      Person("Leia", "Bose"),
      Person("Meghann", "Rhynes"),
      Person("Lelia", "Harbuck"),
      Person("Valery", "Lazo"),
      Person("Charlene", "Ganser"),
      Person("Akilah", "Desanto"),
      Person("Cyril", "Matamoros"),
      Person("Porsche", "Forward"),
      Person("Brooks", "Mckinsey"),
    )
  }

  def getNamesAsyncFuture(number: Integer): Future[List[Person]] = {
    Future {
      val persons = getNames
      if (number > persons.size) {
        throw new IllegalArgumentException(s"Invalid number of persons: $number")
      }
      persons.slice(0, number)
    }
  }

  def getDoubledNamesAsyncFuture(number: Integer): Future[List[Person]] = {
    for {
      f1Res <- getNamesAsyncFuture(number)
      f2Res <- getNamesAsyncFuture(number)
    } yield f1Res ++ f2Res
  }

  def getNamesAsyncPromise(number: Integer): Future[List[Person]] = {
    val promise = Promise[List[Person]]()
    Future {
      val persons = getNames
      if (number > persons.size) {
        promise.failure(new IllegalArgumentException(s"Invalid number of persons: $number"))
      } else {
        promise.success(persons.slice(0, number))
      }
    }
    promise.future
  }

  def getDoubledNamesAsyncPromiseWithError(number: Integer): Future[List[Person]] = {
    for {
      f1Res <- getNamesAsyncPromise(number + 20)
      f2Res <- getNamesAsyncPromise(number)
    } yield f1Res ++ f2Res
  }

  private def instantiateDedicatedExecutionContext(): ExecutionContext = {
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(Runtime.getRuntime.availableProcessors))
  }

}
