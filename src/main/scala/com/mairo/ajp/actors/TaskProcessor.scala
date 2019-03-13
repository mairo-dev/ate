package com.mairo.ajp.actors

import java.util.UUID

import akka.actor.{Actor, ActorLogging, Props}
import com.mairo.ajp.domains.{Task, TaskState}
import com.mairo.ajp.dtos.CreateTaskDtoIn

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 07.11.2018
  */

class TaskProcessor extends Actor with ActorLogging {
  override def receive: Receive = {
    case x:CreateTaskDtoIn => sender ! UUID.randomUUID().toString
    case _ =>  sender() ! "DEFAULT BEHAVIOUR"
    }
  }

object TaskProcessor{
  def props():Props = Props(new TaskProcessor())
}
