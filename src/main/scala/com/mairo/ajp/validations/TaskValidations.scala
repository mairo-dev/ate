package com.mairo.ajp.validations
import java.time.{ZoneOffset, ZonedDateTime}

import com.mairo.ajp.dtos.CreateTaskDtoIn
import com.wix.accord.dsl._


object TaskValidations {

  implicit val localDateOrdering: Ordering[ZonedDateTime] = Ordering.by(_.toEpochSecond)


  implicit val createTaskDtoInValidation = validator[CreateTaskDtoIn]{ task =>
    task.queue is notEmpty
    task.notBeforeTime should be > ZonedDateTime.now(ZoneOffset.UTC)
  }
}
