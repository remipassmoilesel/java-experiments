package org.remipassmoilesel

import scala.util.Random

object Utils {

  val random = Random

  def randomSleep(): Unit = {
    val sleepTime = 200 + random.nextInt(400)
    Thread.sleep(sleepTime)
  }
}
