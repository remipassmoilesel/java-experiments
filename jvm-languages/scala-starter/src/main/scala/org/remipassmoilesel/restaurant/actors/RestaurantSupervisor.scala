package org.remipassmoilesel.restaurant.actors

import akka.actor.{Actor, ActorLogging, Props}


object RestaurantSupervisor {
  def props(): Props = Props(new RestaurantSupervisor)
}


class RestaurantSupervisor extends Actor with ActorLogging {

  val waiter = context.actorOf(WaiterActor.props("waiter-1"))

  override def preStart(): Unit = log.info(s"Started")

  override def postStop(): Unit = log.info(s"Stopped")

  override def receive: Receive = {
    case GetMenu =>
      log.info("Received GetMenu request: " + sender())
      waiter ! RequestCtr(GetMenu, sender())
    case unexpectedMessage =>
      sender() ! new Exception("Unexpected message: " + unexpectedMessage)
  }

}