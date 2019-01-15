package org.remipassmoilesel.restaurant

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import org.remipassmoilesel.restaurant.actors.GetMenu

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

class RestaurantService(restaurantSupervisor: ActorRef) {

  implicit val timeout: Timeout = Timeout(1.seconds)

  def getMenu: Future[Any] = {
    restaurantSupervisor ? GetMenu
  }

}
