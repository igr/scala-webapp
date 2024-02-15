package dev.oblac.prwect.resource

import dev.oblac.prwect.model.Book
//import io.circe.{Codec, Decoder, Encoder}
//import sttp.tapir.*
//import sttp.tapir.json.circe.*
//import sttp.tapir.generic.auto.*
import io.circe.generic.auto.*

//implicit val bookTitleCodec: Codec[BookTitle] =
//  Codec.from(Decoder.decodeString.map(BookTitle.apply), Encoder.encodeString.contramap(_.value))
//implicit val bookYearCodec: Codec[BookYear] =
//  Codec.from(Decoder.decodeInt.map(BookYear.apply), Encoder.encodeInt.contramap(_.value))

case class BookJson(title: String, year: Int)
object BookJson {
  def fromBook(book: Book): BookJson = BookJson(book.title.value, book.year.value)
}

//implicit val bookJsonCodec: Codec[BookJson] = deriveCodec
//implicit val bookJsonDecoder: Decoder[BookJson] = deriveDecoder
//implicit val bookJsonEncoder: Encoder[BookJson] = deriveEncoder
