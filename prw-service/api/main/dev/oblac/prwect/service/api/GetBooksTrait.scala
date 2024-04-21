package dev.oblac.prwect.service.api

import dev.oblac.prwect.model.Book

trait GetBooksTrait[F[_]] extends (() => F[Seq[Book]])
