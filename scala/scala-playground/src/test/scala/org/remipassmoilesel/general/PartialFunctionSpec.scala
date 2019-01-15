package org.remipassmoilesel.general

import org.scalatest.{FunSpec, Matchers}

case class Message(id: Int)

class PartialFunctionSpec extends FunSpec with Matchers {

  describe("Partial functions experiments") {

    it("Partial function without default case") {

      val handleSpecialNumber = (message: Message) => {
        "Message: " + message.id
      }

      val partial1: PartialFunction[Any, Any] = {
        case arg: String => arg + " " + arg
        case arg: Int if arg > 10 && arg < 20 => arg * 2
        case Message(id) => handleSpecialNumber(Message(id))
      }

      partial1.isDefinedAt("hello") should be(true)
      partial1("hello") should equal("hello hello")

      partial1.isDefinedAt(3) should be(false)
      partial1.isDefinedAt(11) should be(true)
      partial1(11) should equal(22)

      partial1.isDefinedAt(Message(2)) should be(true)
      partial1(Message(2)) should equal("Message: 2")

    }

    it("Partial function with default case") {

      val handleDefault = (param: Any) => {
        "Unknown argument: " + param.toString
      }

      val partial1: PartialFunction[Any, Any] = {
        case arg: String => arg + " " + arg
        case unknownArg => handleDefault(unknownArg)
      }

      partial1.isDefinedAt("hello") should be(true)
      partial1("hello") should equal("hello hello")

      partial1.isDefinedAt(11) should be(true)
      partial1(11) should equal("Unknown argument: 11")

    }

  }

}
