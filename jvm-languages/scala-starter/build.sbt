import Dependencies._

lazy val root = (project in file(".")).
  settings(
    name := "scala-starter",
    libraryDependencies +=  scalaTest % Test,
    libraryDependencies +=  akkaActors
  )
