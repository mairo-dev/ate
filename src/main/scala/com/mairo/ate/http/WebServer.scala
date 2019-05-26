package com.mairo.ate.http

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 07.11.2018
  */

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.mairo.ate.domains.{Task, TaskState}
import spray.json.{JsObject, JsString}
import TaskDirective._
import com.mairo.ate.actors.TaskProcessor

import scala.concurrent.duration._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.Future
import scala.io.StdIn

object WebServer extends JsonSupport {
  def main(args: Array[String]) {

    implicit val system = ActorSystem("atp-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher
    implicit val timeout: Timeout = Timeout(5 seconds)

    val taskProcessor = system.actorOf(TaskProcessor.props(), "taskProcessor")

    val taskRoute = new TaskRoute(taskProcessor).taskRoute


    val route: Route = taskRoute


    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}