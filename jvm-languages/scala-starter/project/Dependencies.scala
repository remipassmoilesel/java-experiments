import sbt._

object Dependencies {
  lazy val akkaActors = "com.typesafe.akka" %% "akka-actor" % "2.5.18"
  lazy val akkaActorsTyped = "com.typesafe.akka" %% "akka-actor-typed" % "2.5.18"
  lazy val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % "2.5.18"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
}
