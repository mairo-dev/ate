package com.mairo.ajp.actors

import akka.actor.{Actor, ActorLogging, Props}
import com.mairo.ajp.domains.{Task, TaskState}

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 07.11.2018
  */

class TaskProcessor extends Actor with ActorLogging {
  override def receive: Receive = {
    case Task(uri,_,_,_,_) => uri match {
      case "ololo" => sender() ! "Absolutely Ololo"
        case _ =>  sender() ! "Not ololo"
    }
  }
}

object TaskProcessor{
  def props():Props = Props(new TaskProcessor())
}
