name := "spark-streaming-tweets-data"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.13.2",
  "org.apache.spark" %% "spark-sql" % "2.13.2"
)