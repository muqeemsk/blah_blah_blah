
organization := "com.agilone"

// Use lowercase name to match artifact naming
name := "templateproject"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.4"

crossPaths := false

resolvers ++= Seq(
  "A1_snapshots" at "https://jfrog.ais.acquia.io/artifactory/libs-snapshot-local",
  "A1_release" at "https://jfrog.ais.acquia.io/artifactory/libs-release-local",
  "A1_release_candidates" at "https://jfrog.ais.acquia.io/artifactory/libs-release-candidate-local",
  "A1_remote" at "https://jfrog.ais.acquia.io/artifactory/remote-repos"
)

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % "test->default",
  "junit" % "junit-dep" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)

// Test settings
testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")

// Coverage settings
jacocoReportSettings in Test := JacocoReportSettings()
  .withTitle("Template Project Coverage Report")
  .withFormats(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML)

// Other settings
findbugsSettings

//packaging settings
enablePlugins(JavaServerAppPackaging)

// Required for Debian packages
bashScriptConfigLocation := Some("${app_home}/../conf/jvmopts")

packageDescription in Linux := "Template projects for Polaris"

maintainer in Linux := "FirstName LastName <FistName.LastName@agilone.com>"

packageSummary in Linux := "Serves as a demonstration on setting up Polaris projects"

daemonUser in Linux := "templateproject"

daemonGroup in Linux := "templateproject"

name in Debian := name.value.toLowerCase()

//Required to create .POM but not used
publishTo := Some("A1PublishRepo" at "https://jfrog.ais.acquia.io/artifactory")

// FAT JAR settings
/**
mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case "application.conf" => MergeStrategy.concat
    case "unwanted.txt"     => MergeStrategy.discard
    case "META-INF/MANIFEST.MF" => MergeStrategy.discard
    case x => MergeStrategy.first
  }
}

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

test in assembly := {}

jarName in assembly := name.value + "_FATJAR-" + version.value + ".jar"

mappings in Universal := {
    // universalMappings: Seq[(File,String)]
    val universalMappings = (mappings in Universal).value
    val fatJar = (assembly in Compile).value
    // removing means filtering
    val filtered = universalMappings filter {
        case (file, name) =>  ! name.endsWith(".jar")
    }
    // add the fat jar
    filtered :+ (fatJar -> ("lib/" + fatJar.getName))
}


// the bash scripts classpath only needs the fat jar
scriptClasspath := Seq( (jarName in assembly).value )
**/
