
lazy val root = (project in file(".")).
  enablePlugins(JavaAppPackaging).
  settings(
    name := "WDAEServer",
    version := "1.0",
    scalaVersion := "2.11.8"
  )