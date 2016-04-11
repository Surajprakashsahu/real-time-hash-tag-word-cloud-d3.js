name := "firstSparkApp"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.apache.spark"  %% "spark-core"              % "1.0.1",
  "com.typesafe.akka" %% "akka-actor"              % "2.2.3", 
  "com.typesafe.akka" %% "akka-slf4j"              % "2.2.3",
  "org.apache.spark"  %% "spark-streaming-twitter" % "1.0.1",
  "org.apache.spark"  %% "spark-sql"               % "1.0.1",
  "org.apache.spark"  %% "spark-mllib"             % "1.0.1",
  "com.googlecode.json-simple" % "json-simple"     % "1.1",
  "org.json4s" %% "json4s-native" % "3.3.0",
  "org.json4s" %% "json4s-jackson" % "3.3.0"
  
  )     

  
play.Project.playScalaSettings
