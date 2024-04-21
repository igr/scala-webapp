package dev.oblac.prwect.service.impl

import cats.~>
import dev.oblac.prwect.db.api.BookRepository
import dev.oblac.prwect.model.*
import dev.oblac.prwect.service.api.GetBooksTrait

class GetBooks[F[_], DB[_]](bookRepository: BookRepository[DB])
                           (implicit transact: DB ~> F)
    extends GetBooksTrait[F] {

  override def apply(): F[Seq[Book]] =
    transact {
      bookRepository.fetchBooks()
    }
}
