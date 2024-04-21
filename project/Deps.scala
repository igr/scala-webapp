import sbt.*

object Deps {
  object Cats extends Dep("org.typelevel", "2.10.0") {
    val Core = module("cats-core")
  }
  val CatsEffect = "org.typelevel" %% "cats-effect" % "3.5.3"

  object Http4s extends Dep("org.http4s", "0.23.23") {
    val Core = module("http4s-core")
    val Circe = module("http4s-circe")
    val Ember = module("http4s-ember-server")
  }

  object Circe extends Dep("io.circe", "0.14.6") {
    val Core = module("circe-core")
    val Generic = module("circe-generic")
    val Parser = module("circe-parser")
    val Derivation = module("circe-derivation")
  }

  object Tapir extends Dep("com.softwaremill.sttp.tapir", "1.9.9") {
    val Http4sServer = module("tapir-http4s-server")
    val Circe = module("tapir-json-circe")
    val Swagger = module("tapir-swagger-ui-bundle")
  }

  object Slick extends Dep("com.typesafe.slick", "3.5.1") {
    val Core = module("slick")
    val Codegen = module("slick-codegen")
    val Hikari = module("slick-hikaricp")
  }
  val JdbcPostgresql = "org.postgresql" % "postgresql" % "42.7.3"

  val TypesafeConfig = "com.typesafe" % "config" % "1.4.3"

  object Slf4j extends Dep("org.slf4j", "2.0.13") {
    val Api = module("slf4j-api", crossVersion = false)
    val Simple = module("slf4j-simple", crossVersion = false)
  }



  abstract class Dep(val organization: String, val version: String) {

    protected def module(artifact: String,
                         organization: String = organization,
                         version: String = version,
                         crossVersion: Boolean = true): ModuleID =
      (if (crossVersion) organization %% artifact else organization % artifact) % version
  }
}
