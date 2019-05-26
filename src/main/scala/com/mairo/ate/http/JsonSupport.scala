package com.mairo.ate.http

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.mairo.ate.domains.{Retries, Task, TaskState}
import com.mairo.ate.dtos.FailureMessage
import com.mairo.ate.dtos.general.TaskDescription
import com.mairo.ate.dtos.task.{CreateTaskDtoIn, CreateTaskDtoOut}
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat}

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 08.11.2018
  */

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  import com.mairo.ate.http.DateMarshalling._


  implicit val taskStateFormat = new EnumJsonConverter(TaskState)
  implicit val taskDescriptionFormat = jsonFormat4(TaskDescription)
  implicit val taskRetriesFormat = jsonFormat3(Retries)
  implicit val taskFormat = jsonFormat9(Task)
  implicit val failureMessage = jsonFormat2(FailureMessage)

  implicit val optionTaskDescriptionFormat = optionFormat[TaskDescription]
  implicit val createTaskDtoInFormat = jsonFormat5(CreateTaskDtoIn)
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

  //  class InstantJsonFormat extends RootJsonFormat[ZonedDateTime] {
  //    val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
  //
  //    def write(i: ZonedDateTime) =
  //      JsString(formatter.format(i))
  //
  //    def read(value: JsValue) = value match {
  //      case JsString(s) ⇒ ZonedDateTime.from(formatter.parse(s))
  //      case _ ⇒ throw DeserializationException(s"String expected but got $value")
  //    }
  //  }

}
