package io.github.visborne.nibble.plugin

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

class Nibble extends JavaPlugin {
  val plugin = this
  override def onEnable(): Unit = {
    Bukkit.getLogger().info("XKCD")
    WSServer.start()
  }


  object WSServer extends WebSocketServer(new InetSocketAddress(42069)){
    val console = Bukkit.getServer().getConsoleSender();
    
    override def onOpen(x$1: WebSocket, x$2: ClientHandshake): Unit =  
      Bukkit.getLogger().info("Websocket Has Connected")

    override def onClose(x$1: WebSocket, x$2: Int, x$3: String, x$4: Boolean): Unit = 
      Bukkit.getLogger().info("Websocket Has Disconnected")

    override def onMessage(x$1: WebSocket, m: String): Unit = {
      Bukkit.getLogger().info(s"Websocket has sent a message: $m")
      val str = m match {
        case "Zombie" =>  "minecraft:zombie"
        case "Creeper" => "minecraft:creeper"
        case "Skeleton" => "minecraft:skeleton"
      }
      Bukkit.getLogger().info(s"here 1")
      Bukkit.getLogger().info(s"summon $str 230 87 159")
      try{
        
        val callable:java.util.concurrent.Callable[Unit] = () => Bukkit.dispatchCommand(console,s"summon $str 230 87 159")
        Bukkit.getScheduler().callSyncMethod(plugin,callable)
      
      }catch{
        case e => Bukkit.getLogger().info(s"FUCK 1 $e")
      }
      /*
      Bukkit.getLogger().info(s"/summon $str 230 87 159")
      try{Bukkit.dispatchCommand(console,s"/summon $str 230 87 159")}catch{
        case e => Bukkit.getLogger().info(s"FUCK 2 $e")
      }*/
      Bukkit.getLogger().info(s"here 2")
      /*
      for( playero <- Bukkit.getOnlinePlayers().toArray ){
        val player = playero.asInstanceOf[Player]
        Bukkit.dispatchCommand(console,s"summon $str ${player.getLocation.getBlockX} ${player.getLocation.getBlockY} ${player.getLocation.getBlockZ}")
      }*/

    }

    override def onError(x$1: WebSocket, e: Exception): Unit = 
      Bukkit.getLogger().info(s"Websocket has errored ${e.getCause}")

    override def onStart(): Unit = 
      Bukkit.getLogger().info(s"Websocket server started")

    
  }

}

