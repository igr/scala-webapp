package dev.oblac.prwect.db.repo

import dev.oblac.prwect.db.api.BookRepository
import dev.oblac.prwect.model.Book
import slick.dbio.DBIO

class BookDbRepo extends BookRepository[DBIO] {

  override def findBooks(): DBIO[Seq[Book]] = ???
}
