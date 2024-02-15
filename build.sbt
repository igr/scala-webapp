val projectId = "prwect"
name := projectId

Settings.global(projectId)

lazy val core = project
  .in(file("core"))
  .settings(Settings.common)
  .settings(
    name := "core",
    libraryDependencies ++= Seq(
      Deps.Cats.Core,
      Deps.CatsEffect,
    )
  )

lazy val server = project
  .in(file("prw-server"))
  .dependsOn(core)
  .settings(Settings.common)
  .settings(
    name := "server",
    libraryDependencies ++= Seq(
      Deps.Http4s.Core,
      Deps.Http4s.Dsl,
      Deps.Http4s.Circe,
      Deps.BlazeServer,
    ),
    run / fork := true
  )

