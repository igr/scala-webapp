package dev.oblac.prwect.model

case class BookId(value: Int) extends AnyVal

case class BookTitle(value: String) extends AnyVal

case class BookYear(value: Int) extends AnyVal

case class NewBook(title: BookTitle, year: BookYear, author: Author)
case class Book(id: BookId, title: BookTitle, year: BookYear, author: Author)
