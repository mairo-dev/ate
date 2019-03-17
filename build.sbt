name := "ate"

version := "0.1"

scalaVersion := "2.12.8"

lazy val akka = "com.typesafe.akka" %% "akka-actor" % "2.5.17"
lazy val akkaStreams = "com.typesafe.akka" %% "akka-stream" % "2.5.17"
lazy val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.1.5"
lazy val akkaTest ="com.typesafe.akka" %% "akka-testkit" % "2.5.17" % Test
lazy val akkaStreamTest = "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.17" % Test
lazy val akkaHttpTest = "com.typesafe.akka" %% "akka-http-testkit" % "10.1.5" % Test
//lazy val mongo = "org.reactivemongo" %% "reactivemongo" % "0.16.0"
lazy val jsonSpray = "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.5"
lazy val slick =    "com.typesafe.slick" %% "slick" % "3.2.3"
lazy val slickHicari = "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3"
lazy val slf4j = "org.slf4j" % "slf4j-nop" % "1.6.4"
lazy val accord = "com.wix" %% "accord-core" % "0.7.3"
lazy val mysql = "mysql" % "mysql-connector-java" % "8.0.15"


libraryDependencies ++= Seq(
  akka, akkaStreams, akkaHttp,
//  mongo, 
  mysql,
  jsonSpray,
  slick,
  slickHicari,
  slf4j,
  accord)