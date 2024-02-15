package dev.oblac.prwect.routes

import cats.effect.*
import dev.oblac.prwect.service.api.BookApi
import dev.oblac.prwect.resource.BookJson
import io.circe.generic.auto.*
import sttp.tapir.*
import sttp.tapir.json.circe.*
import sttp.tapir.generic.auto.*
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.swagger.bundle.SwaggerInterpreter


case class User(name: String) extends AnyVal

class Endpoints(bookApi: BookApi[IO]):
  val helloEndpoint: PublicEndpoint[User, Unit, String, Any] = endpoint.get
    .in("hello")
    .in(query[User]("name"))
    .out(stringBody)
  val helloServerEndpoint: ServerEndpoint[Any, IO] =
    helloEndpoint.serverLogicSuccess(user => IO.pure(s"Hello ${user.name}"))

  val booksListing: PublicEndpoint[Unit, Unit, List[BookJson], Any] = endpoint.get
    .in("books" / "list" / "all")
    .out(jsonBody[List[BookJson]])
  val booksListingServerEndpoint: ServerEndpoint[Any, IO] =
    booksListing.serverLogicSuccess(_ => bookApi.getBooks().map(_.map(BookJson.fromBook)))


  val apiEndpoints: List[ServerEndpoint[Any, IO]] =
    List(helloServerEndpoint, booksListingServerEndpoint)


  val docEndpoints: List[ServerEndpoint[Any, IO]] = SwaggerInterpreter()
    .fromServerEndpoints[IO](apiEndpoints, "agricultural-swift", "1.0.0")


  val all: List[ServerEndpoint[Any, IO]] = apiEndpoints ++ docEndpoints
