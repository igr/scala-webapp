package dev.oblac.prwect

import slick.jdbc.JdbcProfile

trait DatabaseManager[F[_], DB[_]] {
  def run[A](action: DB[A]): F[A]
  def runTx[A](action: DB[A])(implicit profile: JdbcProfile): F[A]    // todo remove implicit param
  def sequence[A](action: Seq[DB[A]]): DB[Seq[A]]
}
