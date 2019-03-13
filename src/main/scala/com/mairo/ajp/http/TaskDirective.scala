package com.mairo.ajp.http


import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directive
import akka.http.scaladsl.server.Directives.complete
import com.mairo.ajp.dtos.{CreateTaskDtoIn, FailureMessage}
import com.wix.accord._
import com.mairo.ajp.validations.TaskValidations._

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 08.11.2018
  */

object TaskDirective extends JsonSupport {

  def validateTask(dtoIn: CreateTaskDtoIn): Directive[Unit] = {
    Directive { inner =>
      val validationResult: Result = validate(dtoIn)
      if (validationResult.isSuccess) {
        inner(())
      } else {
        complete(StatusCodes.BadRequest -> FailureMessage(400, validationResult.toString))
      }
    }

  }
}
