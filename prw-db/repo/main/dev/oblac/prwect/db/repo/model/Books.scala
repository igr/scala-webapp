package dev.oblac.prwect.db.repo.model

import slick.lifted.{Rep, TableQuery, Tag}

import slick.jdbc.PostgresProfile.api._

class Books(tag: Tag) extends Table[BookDbRow](tag, "books") {

  def bookId: Rep[Int] = column[Int]("book_id")
  def title: Rep[String] = column[String]("title")
  def year: Rep[Int] = column[Int]("year")

  override def * = (bookId, title, year).mapTo[BookDbRow]
}
object Books {
  val allBooks = TableQuery[Books]
}
