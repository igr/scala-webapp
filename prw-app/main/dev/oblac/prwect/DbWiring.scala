package dev.oblac.prwect

import cats.effect.IO
import cats.~>
import com.typesafe.config.Config
import dev.oblac.prwect.db.api.BookRepository
import dev.oblac.prwect.db.repo.SlickBookRepository
import dev.oblac.prwect.transactor.Transactor
import slick.basic.DatabaseConfig
import slick.dbio.DBIO
import slick.jdbc.JdbcProfile

trait DbWiring[F[_]] {

  def transact[G[_]]: F ~> G

  // repositories
  val bookRepository: BookRepository[F]
}

private class DbWiringImpl(private val postgresConfig: Config)
  extends DbWiring[DBIO] {

  // database configuration and connection
  private val databaseConfig = DatabaseConfig.forConfig[JdbcProfile]("postgres", postgresConfig)
  private val dbProfile = databaseConfig.profile

  // repositories
  override val bookRepository: BookRepository[DBIO] = new SlickBookRepository(dbProfile)

  // the transactor implementation (!) // todo is there a better way ?
  private val transactorResource = Transactor
    .fromDatabaseConfig[IO](databaseConfig)
    .map(_.configure(Transactor.transactionally(databaseConfig.profile))) // or any DBIO ~> DBIO
    //.use(_.transact(action))


  override def transact[G[_]]: DBIO ~> G =
    new(DBIO ~> G) {
      override def apply[A](dbio: DBIO[A]): G[A] = {
        transactorResource.use(_.transact(dbio)).asInstanceOf[G[A]] // todo I had to cast it, can it be avoided?
      }
    }

}
