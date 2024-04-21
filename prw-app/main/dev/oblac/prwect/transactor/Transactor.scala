package dev.oblac.prwect.transactor

import cats.effect.kernel.{Async, Resource, Sync}
import cats.~>
import slick.basic.DatabaseConfig
import slick.dbio.DBIO
import slick.jdbc.{JdbcBackend, JdbcProfile}

trait Transactor[F[_]] {
  def transact[A](dbio: DBIO[A]): F[A] = transactK(dbio)
  def transactK: DBIO ~> F

  def configure(f: DBIO ~> DBIO): Transactor[F] = Transactor
    .liftK(f andThen transactK)
}
object Transactor {
  def apply[F[_]](implicit F: Transactor[F]): Transactor[F] = F

  /** Creates a Transactor from a factory of databases. This transactor doesn't
    * actually run the DBIO actions in transactions by default.
    */
  def fromDatabase[F[_]: Async](
      dbF: F[JdbcBackend#Database]
  ): Resource[F, Transactor[F]] = Resource
    .make(dbF)(db => Async[F].fromFuture(Sync[F].delay(db.shutdown)))
    .map { db =>
      liftK {
        new (DBIO ~> F) {
          def apply[A](fa: DBIO[A]): F[A] = Async[F]
            .fromFuture(Sync[F].delay(db.run(fa)))
        }
      }
    }

  /** Creates a Transactor from a DatabaseConfig. */
  def fromDatabaseConfig[F[_]: Async](
      dbConfig: DatabaseConfig[? <: JdbcProfile]
  ): Resource[F, Transactor[F]] = fromDatabase(Sync[F].delay(dbConfig.db))

  def liftK[F[_]](f: DBIO ~> F): Transactor[F] = {
    new Transactor[F] {
      override val transactK: DBIO ~> F = f
    }
  }

  /** Runs the DBIO in a transaction
    */
  def transactionally(implicit profile: JdbcProfile): DBIO ~> DBIO = {
    import profile.api.{DBIO as _, *}
    new (DBIO ~> DBIO) {
      def apply[A](fa: DBIO[A]): DBIO[A] = fa.transactionally
    }
  }

}
