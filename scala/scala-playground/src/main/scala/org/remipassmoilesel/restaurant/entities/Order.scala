package org.remipassmoilesel.restaurant.entities

import java.time.LocalDate
import java.util.UUID

case class Order(customer: Customer, articles: List[Article], date: LocalDate, uuid: String) {

}

object Order {
  def apply(customer: Customer, articles: List[Article]): Order = {
    val now = LocalDate.now()
    val orderId = UUID.randomUUID().toString
    new Order(customer, articles, now, orderId)
  }
}