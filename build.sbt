val scala3Version = "3.3.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "record-my-favs",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "com.amazonaws" % "aws-lambda-java-core" % "1.2.2",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % "0.14.5",
      "io.circe" %% "circe-generic" % "0.14.5",
      "io.circe" %% "circe-parser" % "0.14.5"
    ),
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x                             => MergeStrategy.first
}
