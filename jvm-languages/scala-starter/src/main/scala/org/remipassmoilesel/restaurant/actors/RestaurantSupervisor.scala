package org.remipassmoilesel.restaurant.actors

import akka.actor.{Actor, ActorLogging, Props}


object RestaurantSupervisor {
  def props(): Props = Props(new RestaurantSupervisor)
}


class RestaurantSupervisor extends Actor with ActorLogging {

  val waiter = context.actorOf(WaiterActor.props("waiter-1"))

  override def preStart(): Unit = log.info(s"Started")

  override def postStop(): Unit = log.info(s"Stopped")

  override def receive = Actor.emptyBehavior

}