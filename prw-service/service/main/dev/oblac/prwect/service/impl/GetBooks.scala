package dev.oblac.prwect.service.impl

import cats.effect.Sync
import dev.oblac.prwect.service.api.GetBooksTrait
import dev.oblac.prwect.model.*

class GetBooks[F[_]](implicit F: Sync[F]) extends GetBooksTrait[F]:
  override def apply(): F[List[Book]] =
    F.pure(
      List(
        Book(
          BookId(1),
          BookTitle("Book 1"),
          BookYear(2003),
          Author(AuthorName("Author 1"))
        )
      )
    )
