import sbt.Keys.*
import sbt.*

import scalafix.sbt.ScalafixPlugin.autoImport.*

object Settings {
  val ScalacOptions: Seq[String] = Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-explaintypes",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-unchecked",
    "-Werror",
    "-Wunused:implicits",
    "-Wunused:imports",
    "-Wunused:linted",
    "-Wunused:locals",
    "-Wunused:params",
    "-Wunused:privates",
    "-Wvalue-discard",
  )

  val common: Seq[Setting[?]] =
    compile

  def global(projectId: String): Seq[Setting[?]] =
    common ++ Seq(
      ThisBuild / organization := s"dev.oblac.$projectId",
      ThisBuild / versionScheme := Some("early-semver"),

      ThisBuild / semanticdbEnabled := true,
      ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0"
    )

  private lazy val compile: Seq[Setting[?]] =
    Seq(
      scalaVersion := "3.4.0",
      scalacOptions ++= ScalacOptions ++ Seq(
        "-Ybackend-parallelism", Math.max(java.lang.Runtime.getRuntime.availableProcessors() - 1, 1).toString
      ),

      Compile / scalaSource := baseDirectory.value / "main",
      Compile / resourceDirectory := baseDirectory.value / "resources",

      Test / scalaSource := baseDirectory.value / "test",
      Test / resourceDirectory := baseDirectory.value / "test-resources",

      Compile / doc /sources := Seq.empty,
      Compile / packageDoc/ publishArtifact := false,

      sourcesInBase := false,

      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "3.2.15" % Test
      )
    )

}
