import sbt._
import Keys._

object B extends Build
{
  lazy val root =
    Project("root", file(".")).
      configs( itTest ).
      settings( inConfig(itTest)(Defaults.testSettings) : _*).
      settings( libraryDependencies += specs )

    lazy val itTest = config("it") extend(Test)
    lazy val specs = "org.specs2" %% "specs2" % "2.0" % "it,test"
}
