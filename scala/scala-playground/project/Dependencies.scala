import sbt._

object Dependencies {
  val akaVersion = "2.5.18"

  lazy val akkaActors = "com.typesafe.akka" %% "akka-actor" % akaVersion
  lazy val akkaActorsTyped = "com.typesafe.akka" %% "akka-actor-typed" % akaVersion
  lazy val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % akaVersion

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
}
