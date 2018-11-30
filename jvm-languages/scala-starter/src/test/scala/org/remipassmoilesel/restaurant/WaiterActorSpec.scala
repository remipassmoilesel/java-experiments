package org.remipassmoilesel.restaurant

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import org.remipassmoilesel.restaurant.actors.{GetMenu, RequestCtr, ResponseCtr, WaiterActor}
import org.remipassmoilesel.restaurant.entities._
import org.scalatest._

class WaiterActorSpec extends TestKit(ActorSystem("WaiterActorSpec"))
  with ImplicitSender
  with FunSpecLike
  with Matchers
  with BeforeAndAfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  describe("WaiterActorSpec") {

    it("Should reply to GetMenu message") {
      val waiter = system.actorOf(WaiterActor.props("waiter-1"))
      waiter ! RequestCtr(GetMenu, testActor)
      expectMsg(ResponseCtr(Menu(Articles.AllDishes, Articles.AllBeverages)))
    }

  }

}
