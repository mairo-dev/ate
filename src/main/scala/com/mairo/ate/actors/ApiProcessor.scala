package com.mairo.ate.actors

import akka.actor.{Actor, ActorLogging, ActorRef}
import com.mairo.ate.actors.conf.ActorService
import com.mairo.ate.http.ImperativeRequestContext

class ApiProcessor extends Actor
  with ActorLogging
  with ActorService {

  var requestsState: Map[String, ImperativeRequestContext] = Map.empty

  val taskProcessor: ActorRef = context.actorOf(TaskProcessor.props())
}
