package org.remipassmoilesel.scalaplayground

import org.junit._
import org.junit.Assert._

@Test
class StreamsTest {

  @Test
  def streamDistinct(): Unit = {
    val stringList: List[String] = List("1", "1", "2", "2", "3")
    assertEquals(3, stringList.toStream.distinct.size)

    val intList: List[Int] = List(1, 1, 2, 3)
    assertEquals(3, stringList.toStream.distinct.size)
  }

}


