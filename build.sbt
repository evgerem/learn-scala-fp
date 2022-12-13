version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "scala-for-feeder"
  )

val catsVersion = "2.9.0"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsVersion
)

scalacOptions ++= Seq(
  "-language:higherKinds"
)
