package dev.oblac.prwect.db.api

import dev.oblac.prwect.model.Book

trait BookRepository[F[_]] {
  def findBooks(): F[Seq[Book]]
}
