package dev.oblac.prwect

import cats.effect.IO
import cats.~>
import com.typesafe.config.{Config, ConfigFactory}
import dev.oblac.prwect.service.api.BookApi
import dev.oblac.prwect.service.impl.GetBooks
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext

/** Main application object, wires the complete application.
  */
object App {
  implicit val ec: ExecutionContext = {
    scala.concurrent.ExecutionContext.Implicits.global
  }

  // wire database
  val config: Config = ConfigFactory.load("application.conf")
  val db: DbWiring[DBIO] = DbWiringImpl(config)

  // define transaction monad transformer
  implicit private val transact: DBIO ~> IO = App.db.transact

  // wire services
  val bookApi: BookApi[IO] = BookApi[IO](getBooks = {
    GetBooks[IO, DBIO](App.db.bookRepository)
  })

}
