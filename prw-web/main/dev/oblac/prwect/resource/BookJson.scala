package dev.oblac.prwect.resource

import dev.oblac.prwect.model.Book
import io.circe.generic.auto.*

case class BookJson(title: String, year: Int)
object BookJson {
  def fromBook(book: Book): BookJson = BookJson(book.title.value, book.year.value)
}
