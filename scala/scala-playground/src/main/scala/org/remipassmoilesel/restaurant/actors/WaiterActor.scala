package org.remipassmoilesel.restaurant.actors

import akka.actor.{Actor, ActorLogging, Props}
import org.remipassmoilesel.restaurant.entities._

object WaiterActor {
  def props(waiterId: String): Props = Props(new WaiterActor(waiterId))
}

class WaiterActor(actorId: String) extends Actor with ActorLogging {

  override def receive: Receive = {
    case RequestCtr(GetMenu, replyTo) =>
      log.info("Received request from: " + replyTo)
      replyTo ! ResponseCtr(getMenu)
  }

  def getMenu: Menu = {
    Menu(Articles.AllDishes, Articles.AllBeverages)
  }

  override def preStart(): Unit = log.info(s"{} started", actorId)

  override def postStop(): Unit = log.info(s"{} stopped", actorId)

}
