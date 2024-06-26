package dev.oblac.prwect

import cats.effect.{ExitCode, IO, IOApp}
import com.comcast.ip4s.{Host, Port, port}
import dev.oblac.prwect.routes.Endpoints
import org.http4s.Response
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router
import sttp.tapir.server.http4s.{Http4sServerInterpreter, Http4sServerOptions}

object Main extends IOApp:

  override def run(args: List[String]): IO[ExitCode] =
    // server
    val serverOptions: Http4sServerOptions[IO] =
      Http4sServerOptions
        .customiseInterceptors[IO]
        .options

    val routes =
      Http4sServerInterpreter[IO](serverOptions).toRoutes(Endpoints(App.bookApi).all)

    val port = sys.env
      .get("HTTP_PORT")
      .flatMap(_.toIntOption)
      .flatMap(Port.fromInt)
      .getOrElse(port"8080")

    EmberServerBuilder
      .default[IO]
      .withHost(Host.fromString("localhost").get)
      .withPort(port)
      .withHttpApp(Router("/" -> routes).orNotFound)
      .build
      .useForever
      .as(ExitCode.Success)
