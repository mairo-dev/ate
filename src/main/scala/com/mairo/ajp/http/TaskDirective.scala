package com.mairo.ajp.http

import java.time.{ZoneOffset, ZonedDateTime}

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directive
import akka.http.scaladsl.server.Directives._
import com.mairo.ajp.dtos.{CreateTaskDtoIn, FailureMessage}


/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 08.11.2018
  */

object TaskDirective extends JsonSupport {

  def validateTask(dtoIn: CreateTaskDtoIn): Directive[Unit] = {
    Directive { inner =>
      var errors = List[String]()

      if (dtoIn.notBeforeTime.isBefore(ZonedDateTime.now(ZoneOffset.UTC))) {
        errors = "[dtoIn.notBeforeTime] property must be present and not empty" :: errors
      }
      if (dtoIn.notAfterTime.isBefore(dtoIn.notBeforeTime)) {
        errors = "[dtoIn.notAfterTime] is before [dtoIn.notBeforeTime]" :: errors
      }
      val commandDescription = dtoIn.call

      if (commandDescription.method.isEmpty) {
        errors = "[call.method] property must be present and not empty" :: errors
      }
      if (!commandDescription.uri.matches("\\b(https?|ftp)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
        errors = "[call.uri] Uri is not valid" :: errors
      }


      errors match {
        case Nil => inner(())
        case _ => complete(StatusCodes.BadRequest -> FailureMessage(400, errors.mkString("\n")))
      }
    }
  }

}
