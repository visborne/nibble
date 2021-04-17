import coursier.maven.MavenRepository
import coursier.core.Authentication
import mill._
import mill.scalalib._
import coursier.Repositories
import mill.modules.Assembly.Rule.ExcludePattern
import mill.modules.Assembly
object nibble extends ScalaModule {
  def scalaVersion = "3.0.0-RC1"
  def repositories = super.repositories ++ Seq(
    coursier.maven.MavenRepository("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")//Repositories.jcenter
  )
  def ivyDeps = Agg(ivy"org.java-websocket:Java-WebSocket:1.5.1"//,ivy"io.github.iltotore::spigot-scala:1.0"
  )
  /*
  def compileIvyDeps = Agg(
    ivy"io.github.iltotore::spigot-scala:1.0"
  )*/
  
  def unmanagedClasspath = T {
    if (!os.exists(millSourcePath / "lib")) Agg()
    else Agg.from(os.list(millSourcePath / "lib").map(PathRef(_)))
  }

  def assemblyRules = Assembly.defaultRules ++ Seq(
    //ExcludePattern("scala/.*"),
    ExcludePattern("org/apache/.*"),
    ExcludePattern("org/spigotmc/.*"),
    ExcludePattern("org/bukkit/.*"),
    ExcludePattern("org/yaml/.*"),
    ExcludePattern("net/.*"),
    ExcludePattern("com/.*"),
    ExcludePattern("script-dir/.*")
  )
}