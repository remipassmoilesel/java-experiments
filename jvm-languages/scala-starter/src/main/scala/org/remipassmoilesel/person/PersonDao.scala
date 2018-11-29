package org.remipassmoilesel.person

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

class PersonDao {

  val random = Random
  implicit val execContext = instantiateExecutionContext()

  def getNames: List[Person] = {
    randomSleep()
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

  def getNamesAsyncFuture(number: Integer) = {
    Future {
      val persons = getNames
      if (number > persons.size) {
        throw new RuntimeException(s"Invalid number of persons: $number")
      }
      persons.slice(0, number)
    }
  }

  private def randomSleep(): Unit = {
    val sleepTime = 200 + random.nextInt(400)
    Thread.sleep(sleepTime)
  }

  private def instantiateExecutionContext(): ExecutionContext = {
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(Runtime.getRuntime.availableProcessors))
  }

}
