package org.remipassmoilesel.scalaplayground

import java.util

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

  @Test
  def convertScalaListToJavaList(): Unit = {
    import scala.collection.JavaConverters._
    val scalaList = List("1", "1", "2", "2", "3")
    val javaList = scalaList.asJava

    assertEquals(javaList.size(), scalaList.size)
  }

  @Test
  def convertJavaListToScalaList(): Unit = {
    import scala.collection.JavaConversions._
    val javaList = util.Arrays.asList("1", "1", "2", "2", "3")
    val scalaList = javaList.toList

    assertEquals(javaList.size(), scalaList.size)
  }

}


