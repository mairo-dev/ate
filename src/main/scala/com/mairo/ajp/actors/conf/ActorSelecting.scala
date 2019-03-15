package com.mairo.ajp.actors.conf

import akka.actor.{Actor, ActorSelection}
import com.typesafe.config.Config

trait ActorSelecting { this: Actor =>

  def locateActor(host: String, port: String, name: String): ActorSelection =
    context.actorSelection(s"akka.tcp://scanner@$host:$port/user/$name")

  def locateRemoteActor(actorAddressConfig: Config): ActorSelection = {
    val host = actorAddressConfig.getString("host")
    val port = actorAddressConfig.getString("port")
    val name = actorAddressConfig.getString("name")
    context.actorSelection(s"akka.tcp://ate@$host:$port/user/$name")
  }

}
