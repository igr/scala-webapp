package dev.oblac.prwect.service.api

case class BookApi[F[_]](
    val getBooks: GetBooksTrait[F]
)
