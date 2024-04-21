package dev.oblac.prwect.db.repo

import dev.oblac.prwect.db.api.BookRepository
import dev.oblac.prwect.db.repo.model.Books
import dev.oblac.prwect.model.Book
import slick.dbio.*
import slick.jdbc.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global

class SlickBookRepository(val jdbcProfile: JdbcProfile)
  extends BookRepository[DBIO] {

  import jdbcProfile.api._

  override def fetchBooks(): DBIO[Seq[Book]] = Books.allBooks.result.map(_.map(_.toModel))
}
