package dev.oblac.prwect.db.repo.model

import dev.oblac.prwect.model.{Book, BookId, BookTitle, BookYear}

case class BookDbRow(id: Int, title: String, year: Int) {   // todo migrate to domain types
  def toModel: Book = Book(BookId(id), BookTitle(title), BookYear(year))
}
