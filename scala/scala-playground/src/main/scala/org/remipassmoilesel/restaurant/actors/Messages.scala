package org.remipassmoilesel.restaurant.actors

import akka.actor.ActorRef
import org.remipassmoilesel.restaurant.entities.{Article, Order}

case class RequestCtr(query: Any, replyTo: ActorRef)

case class ResponseCtr(result: Any)

case object GetMenu

case class GetMenuResponse(articles: List[Article])

case class TakeOrder(value: Order)

case class TakeOrderResponse(estimatedTime: Integer)

case class GetAddition(value: Order)

case class GetAdditionResponse(value: Order)

case class PayOrder(value: Order)

case class PayOrderResponse(value: Order)
