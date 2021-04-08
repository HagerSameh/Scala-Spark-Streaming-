name := "ScalaProject_V3"

version := "0.1"

scalaVersion := "2.12.4"


// https://mvnrepository.com/artifact/org.apache.spark/spark-core

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.7"

// https://mvnrepository.com/artifact/org.apache.spark/spark-sql

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.7"

// https://mvnrepository.com/artifact/org.apache.spark/spark-streaming
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.7" % "provided"



