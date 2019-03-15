package com.mairo.ajp.validations

import java.time.{ZoneOffset, ZonedDateTime}

import com.mairo.ajp.dtos.general.TaskDescription
import com.mairo.ajp.dtos.task.CreateTaskDtoIn
import com.wix.accord._
import com.wix.accord.dsl._


object TaskValidators {
  lazy val httpMethods = Seq("GET", "POST", "PUT", "DELETE")
  implicit val localDateOrdering: Ordering[ZonedDateTime] = Ordering.by(_.toEpochSecond)

  private val validOptionTaskDescription: Validator[Option[TaskDescription]] = v1 => {
    v1 match {
      case Some(td) =>
        if (td.uri.isEmpty) Failure(Set(RuleViolation(td.uri, "is empty")))
        if (!httpMethods.contains(td.method)) Failure(Set(RuleViolation(td.uri, "is empty")))
        Success
      case None =>
        Success
    }
  }
  implicit val taskDescriptionValidator = validator[TaskDescription] { td =>
    td.method is oneOf(httpMethods: _*)
    (td.uri is notEmpty) and ((td.uri should startWith("http://")) or (td.uri should startWith("https://")))
  }

  //  implicit val taskDescriptionValidatorOpt: ValidationTransform.TransformedValidator[Option[TaskDescription]] = validator[Option[TaskDescription]] { otd => {
  //    otd match {
  //      case Some(td) =>
  //        td.method is oneOf(httpMethods: _*)
  //        (td.uri is notEmpty) and ((td.uri should startWith("http://")) or (td.uri should startWith("https://")))
  //      case None => true is true
  //
  //    }
  //  }
  //  }

  private def oneOf[T <: AnyRef](options: T*): Validator[T] =
    new NullSafeValidator[T](
      test = options.contains,
      failure = x => Failure(Set(RuleViolation(x, s"is not one of (${options.mkString(",")})")))
    )


  implicit val createTaskDtoInValidation = validator[CreateTaskDtoIn] { task =>
    task.queue is notEmpty
    task.notBeforeTime should be > ZonedDateTime.now(ZoneOffset.UTC)
    task.call is valid
    task.callback is validOptionTaskDescription
  }

}
