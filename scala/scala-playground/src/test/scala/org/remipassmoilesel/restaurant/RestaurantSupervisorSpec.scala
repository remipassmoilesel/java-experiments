package org.remipassmoilesel.restaurant

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import akka.util.Timeout
import org.remipassmoilesel.restaurant.actors._
import org.scalatest._

import scala.concurrent.duration._

class RestaurantSupervisorSpec extends TestKit(ActorSystem("RestaurantSupervisorSpec"))
  with ImplicitSender
  with AsyncFunSpecLike
  with Matchers
  with BeforeAndAfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  implicit val timeout: Timeout = Timeout(1.seconds)

  private val supervisor = system.actorOf(RestaurantSupervisor.props())
  private val restaurantService = new RestaurantService(supervisor)


  it("Should reply to GetMenu message") {
    // TODO: must return a menu only, or an exception
    restaurantService.getMenu.map(_.isInstanceOf[ResponseCtr] should be(true))
  }

}
