import sbt.*

object Deps {
//  val PostgresJdbc = "org.postgresql" % "postgresql" % "42.5.4"
//  val scalaTest = "org.scalatest" %% "scalatest" % "3.2.9"
//  val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.15.4"
//  val cats = "org.typelevel" %% "cats-core" % "2.6.1"
//  val catsEffect = "org.typelevel" %% "cats-effect" % "3.2.9"
//  val fs2 = "co.fs2" %% "fs2-core" % "3.1.6"
//  val http4sVersion = "0.21.22"
//  val http4sClient = "org.http4s" %% "http4s-blaze-client" % http4sVersion
//  val circeVersion = "0.14.1"
//  val circeCore = "io.circe" %% "circe-core" % circeVersion
//  val circeGeneric = "io.circe" %% "circe-generic" % circeVersion
//  val circeParser = "io.circe" %% "circe-parser" % circeVersion
//  val circeFs2 = "io.circe" %% "circe-fs2" % "0.14.1"
//  val circeGenericExtras = "io.circe" %% "circe-generic-extras" % "0.14.1"
//  val circeOptics = "io.circe" %% "circe-optics" % "0.14.1"
//  val circeJava8 = "io.circe" %% "circe-java8" % "0.14.1"
//  val circeJawn = "io.circe" %% "circe-jawn" % "0.14.1"
//  val circeYaml = "io.circe" %% "circe-yaml" % "0.14.1"
//  val circeDerivation = "io.circe" %% "circe-derivation" % "0.14.1"

  object Cats extends Dep("org.typelevel", "2.10.0") {
    val Core = module("cats-core")
  }
  val CatsEffect = "org.typelevel" %% "cats-effect" % "3.5.3"

  object Http4s extends Dep("org.http4s", "0.23.23") {
    val Core = module("http4s-core")
    val Dsl = module("http4s-dsl")

    val Circe = module("http4s-circe")
  }
  val BlazeServer = "org.http4s" %% "http4s-blaze-server" % "0.23.16"

  abstract class Dep(val organization: String, val version: String) {

    protected def module(artifact: String,
                         organization: String = organization,
                         version: String = version,
                         crossVersion: Boolean = true): ModuleID =
      (if (crossVersion) organization %% artifact else organization % artifact) % version
  }
}
