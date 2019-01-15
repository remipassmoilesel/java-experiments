package org.remipassmoilesel.restaurant.services

import org.remipassmoilesel.restaurant.entities.{Article, Order}

import scala.collection.mutable.ListBuffer

class OrderRepository {

  var db = ListBuffer[Order]()

  def save(order: Order): Unit = {
    db += order
  }

}
