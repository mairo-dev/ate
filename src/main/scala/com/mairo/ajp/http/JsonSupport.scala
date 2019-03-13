package com.mairo.ajp.http

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.mairo.ajp.domains.TaskState.TaskState
import com.mairo.ajp.domains.{Task, TaskState}
import com.mairo.ajp.dtos.{CreateTaskDtoIn, CreateTaskDtoOut, FailureMessage, TaskDescription}
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat}

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 08.11.2018
  */

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val taskStateFormat = new EnumJsonConverter(TaskState)
  implicit val taskFormat = jsonFormat5(Task)
  implicit val failureMessage = jsonFormat2(FailureMessage)
  implicit val zonedDateTimeFormat = new InstantJsonFormat()


  implicit val commandDescriptionDtoFormat = jsonFormat4(TaskDescription)
  implicit val createTaskDtoInFormat = jsonFormat4(CreateTaskDtoIn)
  implicit val createTaskDtoOutFormat = jsonFormat2(CreateTaskDtoOut)


  class EnumJsonConverter[T <: scala.Enumeration](enu: T) extends RootJsonFormat[T#Value] {
    override def write(obj: T#Value): JsValue = JsString(obj.toString)

    override def read(json: JsValue): T#Value = {
      json match {
        case JsString(txt) => enu.withName(txt)
        case somethingElse => throw DeserializationException(s"Expected a value from enum $enu instead of $somethingElse")
      }
    }
  }

  class InstantJsonFormat extends RootJsonFormat[ZonedDateTime] {
    val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME

    def write(i: ZonedDateTime) =
      JsString(formatter.format(i))

    def read(value: JsValue) = value match {
      case JsString(s) ⇒ ZonedDateTime.from(formatter.parse(s))
      case _ ⇒ throw DeserializationException(s"String expected but got $value")
    }
  }

}
