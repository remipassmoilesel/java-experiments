package org.remipassmoilesel.restaurant

import akka.actor.ActorSystem
import org.remipassmoilesel.restaurant.actors.RestaurantSupervisor

import scala.io.StdIn

object RestaurantApp {

  def run() = {
    println("Running RestaurantApp")

    startActorSystem()
  }

  def startActorSystem(): Unit = {
    val system = ActorSystem("restaurant-system")

    try {
      val supervisor = system.actorOf(RestaurantSupervisor.props(), "restaurant-supervisor")
      // Exit the system after ENTER is pressed
      StdIn.readLine()
    } finally {
      system.terminate()
    }
  }

}
