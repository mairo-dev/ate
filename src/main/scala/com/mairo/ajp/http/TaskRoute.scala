package com.mairo.ajp.http

import java.time.{ZoneOffset, ZonedDateTime}

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.mairo.ajp.dtos.general.TaskDescription
import com.mairo.ajp.dtos.task.CreateTaskDtoIn
import com.mairo.ajp.http.TaskDirective._
import spray.json.{JsObject, JsString}

import scala.concurrent.ExecutionContext

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 08.11.2018
  */

class TaskRoute(taskProcessor: ActorRef)
               (implicit ctx: ExecutionContext,
                system: ActorSystem,
                materializer: ActorMaterializer,
                timeout: Timeout) extends JsonSupport {

  val taskRoute: Route =
    pathPrefix("tasks") {
      path("create") {
        post {
          entity(as[CreateTaskDtoIn]) { task =>
            println(task)
            validateTask(task) {
              complete {
                (taskProcessor ? task).mapTo[String]
              }
            }
          }
        }
      } ~
      path("get"/IntNumber){ taskId =>
        get{
          ???
        }
      }~
      path("test"){
        get {
          val data = JsObject("authorId" -> JsString("animal"))
          val desc = TaskDescription("http://google.com", "GET", None, Some(data))
          val task = CreateTaskDtoIn(
            "TEST",
            ZonedDateTime.now(ZoneOffset.UTC).minusDays(2),
            ZonedDateTime.now(ZoneOffset.UTC), desc,None)
          complete(task)
        }
      }
    }
}
