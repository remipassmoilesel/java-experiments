package org.remipassmoilesel.restaurant.actors

import akka.actor.{Actor, ActorLogging, Props}


object RestaurantSupervisor {
  def props(): Props = Props(new RestaurantSupervisor)
}


class RestaurantSupervisor extends Actor with ActorLogging {
  override def preStart(): Unit = log.info(s"$getName started")

  override def postStop(): Unit = log.info(s"$getName stopped")

  override def receive = Actor.emptyBehavior

  def getName: String = {
    classOf[RestaurantSupervisor].getSimpleName
  }
}