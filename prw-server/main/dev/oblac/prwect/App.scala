package dev.oblac.prwect

import cats.data.Kleisli
import cats.effect.{ExitCode, IO, IOApp}
import dev.oblac.prwect.routes.route
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.Router
import org.http4s.{Request, Response}

object App extends IOApp {
  
  private val app: Kleisli[IO, Request[IO], Response[IO]] = Router(
    "/" -> route
  ).orNotFound

  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      //.withoutBanner
      .withHttpApp(app)
      .resource
      .useForever
      .as(ExitCode.Success)

}
