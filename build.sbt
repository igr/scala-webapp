val projectId = "prwect"
name := projectId

Settings.global(projectId)

lazy val modelDomain = project
  .in(file("prw-model"))
  .settings(Settings.common)
  .settings(
    name := "model",
    libraryDependencies ++= Seq(
    )
  )

lazy val util = project
  .in(file("prw-util"))
  .settings(Settings.common)
  .settings(
    name := "util",
    libraryDependencies ++= Seq(
      Deps.Cats.Core,
      Deps.CatsEffect,
    )
  )

lazy val dbApi = project
  .in(file("prw-db/api"))
  .dependsOn(modelDomain)
  .settings(Settings.common)
  .settings(
    name := "db-api",
    libraryDependencies ++= Seq(
    )
  )

lazy val dbImpl = project
  .in(file("prw-db/repo"))
  .dependsOn(modelDomain)
  .dependsOn(dbApi)
  .settings(Settings.common)
  .settings(
    name := "db-impl",
    libraryDependencies ++= Seq(
      Deps.Slick.Core,
      Deps.Slick.Codegen,
    )
  )

lazy val serviceApi = project
  .in(file("prw-service/api"))
  .dependsOn(modelDomain)
  .settings(Settings.common)
  .settings(
    name := "service-api",
    libraryDependencies ++= Seq(
    )
  )

lazy val serviceImpl = project
  .in(file("prw-service/service"))
  .dependsOn(serviceApi)
  .dependsOn(modelDomain)
  .dependsOn(util)
  .dependsOn(dbApi)
  .settings(Settings.common)
  .settings(
    name := "service-impl",
    libraryDependencies ++= Seq(
      Deps.Cats.Core,
      Deps.CatsEffect,
    )
  )

lazy val web = project
  .in(file("prw-web"))
  .dependsOn(modelDomain)
  .dependsOn(serviceApi)
  .dependsOn(util)
  .settings(Settings.common)
  .settings(
    name := "web",
    libraryDependencies ++= Seq(
      Deps.Http4s.Core,
      Deps.Http4s.Circe,
      Deps.Http4s.Ember,
      Deps.Tapir.Http4sServer,
      Deps.Tapir.Circe,
      Deps.Tapir.Swagger
    ),
    run / fork := true
  )

lazy val app = project
  .in(file("prw-app"))
  .dependsOn(web)
  .dependsOn(serviceImpl)
  .settings(Settings.common)
  .settings(
    name := "app",
    libraryDependencies ++= Seq(
      Deps.Cats.Core,
      Deps.CatsEffect,
    )
  )
