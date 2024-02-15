package dev.oblac.prwect.db.repo.model

import dev.oblac.prwect.model.{BookId, BookTitle}

case class BookDbRow(id: BookId, title: BookTitle, year: Int)
