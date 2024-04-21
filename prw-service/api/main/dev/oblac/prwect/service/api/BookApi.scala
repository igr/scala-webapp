package dev.oblac.prwect.service.api

case class BookApi[F[_]](
    getBooks: GetBooksTrait[F]
)
