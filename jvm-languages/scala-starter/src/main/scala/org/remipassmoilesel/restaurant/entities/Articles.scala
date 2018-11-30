package org.remipassmoilesel.restaurant.entities

abstract class Article(val name: String, price: Float)

case object Canneloni extends Article("Canneloni", 8)

case object Lasagna extends Article("Lasagna", 9)

case object Spaghetti extends Article("Spaghetti", 7)

case object Tiramisu extends Article("Tiramisu", 7)

case object WineBottle extends Article("WineBottle", 10)

case object WineGlass extends Article("WineGlass", 2)

object Articles {

  val AllDishes = List(
    Canneloni,
    Lasagna,
    Spaghetti,
    Tiramisu
  )

  val AllBeverages = List(
    WineBottle,
    WineGlass,
  )

}