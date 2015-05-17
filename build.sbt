name := """EmailQueue"""

version := "1.0-SNAPSHOT"

autoScalaLibrary := false

javaOptions ++= Seq("-Dconfig.file=conf/app.conf")

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.35"

libraryDependencies += "com.typesafe" % "config" % "1.2.1"

libraryDependencies += "javax.mail" % "mail" % "1.4"




