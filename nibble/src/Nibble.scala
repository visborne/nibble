package io.github.visborne.nibble.plugin

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Nibble extends JavaPlugin {

  override def onEnable(): Unit = {
    Bukkit.getLogger().info("XKCD")
  }
}