package dev.oblac.prwect.routes

import cats.effect._
import org.http4s._, org.http4s.dsl.io._

val route: HttpRoutes[IO] = HttpRoutes.of[IO] {
  case GET -> Root / "length" / str => Ok(str.length.toString)
}
