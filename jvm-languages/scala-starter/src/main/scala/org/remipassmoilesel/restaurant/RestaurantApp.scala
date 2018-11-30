package org.remipassmoilesel.restaurant

import akka.actor.{ActorRef, ActorSystem}
import org.remipassmoilesel.restaurant.actors.RestaurantSupervisor

object RestaurantApp {

  import scala.concurrent.ExecutionContext.Implicits.global

  def run() = {
    println("Running RestaurantApp")

    val (actorSystem, supervisor) = startActorSystem()
    val restaurantService = new RestaurantService(supervisor)

    restaurantService.getMenu.onComplete(println)
  }

  def startActorSystem(): (ActorSystem, ActorRef) = {
    val system = ActorSystem("restaurant-system")
    val supervisor = system.actorOf(RestaurantSupervisor.props(), "restaurant-supervisor")
    (system, supervisor)
  }

}
