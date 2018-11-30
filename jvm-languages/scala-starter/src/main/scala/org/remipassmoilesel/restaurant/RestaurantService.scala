package org.remipassmoilesel.restaurant

import akka.actor.ActorRef
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

class RestaurantService(rootActor: ActorRef) {

  implicit val timeout = Timeout(1.seconds)

  def getMenu: Future[Any] = {
    Future {
      throw new Exception("Not implemented")
    }
  }

}
