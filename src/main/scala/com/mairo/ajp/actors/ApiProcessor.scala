package com.mairo.ajp.actors

import akka.actor.{Actor, ActorLogging}
import com.mairo.ajp.actors.conf.ActorService
import com.mairo.ajp.http.ImperativeRequestContext

class ApiProcessor extends Actor
  with ActorLogging
  with ActorService {

  var requestsState: Map[String, ImperativeRequestContext] = Map.empty

  val taskProcessor = context.actorOf(TaskProcessor.props())
}
