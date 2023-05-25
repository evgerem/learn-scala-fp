version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "scala-for-feeder"
  )

val catsVersion = "2.9.0"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsVersion,
  "org.typelevel" %% "cats-effect" % "3.2.0"
)

scalacOptions ++= Seq(
  "-language:higherKinds"
)
