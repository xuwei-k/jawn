name := "jawn-benchmarks"

javaOptions in run += "-Xmx6G"

libraryDependencies ++= Seq(
  "io.argonaut" %% "argonaut" % "6.2.3",
  "org.json4s" %% "json4s-native" % "3.6.5",
  "org.json4s" %% "json4s-jackson" % "3.6.5",
  "com.typesafe.play" %% "play-json" % "2.7.3",
  "com.rojoma" %% "rojoma-json" % "2.4.3",
  "com.rojoma" %% "rojoma-json-v3" % "3.9.1",
  "io.spray" %% "spray-json" % "1.3.4",
  "org.parboiled" %% "parboiled" % "2.1.5",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.8",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.9.8",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.8",
  "com.google.code.gson" % "gson" % "2.8.5"
)

// enable forking in run
fork in run := true
