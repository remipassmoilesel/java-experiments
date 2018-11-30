package org.remipassmoilesel.person

import java.util.concurrent.Executors

import org.remipassmoilesel.Utils

import scala.concurrent.{ExecutionContext, Future}

class PersonDao {

  implicit val execContext = instantiateExecutionContext()

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

  def getDoubledNamesAsyncFuture(number: Integer) = {
    for {
      f1Res <- getNamesAsyncFuture(number)
      f2Res <- getNamesAsyncFuture(number)
    } yield (f1Res ++ f2Res)
  }

  def getNamesAsyncFuture(number: Integer) = {
    Future {
      val persons = getNames
      if (number > persons.size) {
        throw new RuntimeException(s"Invalid number of persons: $number")
      }
      persons.slice(0, number)
    }
  }

  private def instantiateExecutionContext(): ExecutionContext = {
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(Runtime.getRuntime.availableProcessors))
  }

}
