package com.mairo.ajp.dtos

import java.time.ZonedDateTime

import spray.json.JsValue

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 07.11.2018
  */

case class TaskDescription(uri: String,
                           method: String,
                           headers: Option[Map[String, String]],
                           data: Option[JsValue])

case class CreateTaskDtoIn(queue:String,
                          notBeforeTime:ZonedDateTime,
                          notAfterTime: ZonedDateTime,
                          call: TaskDescription)

case class CreateTaskDtoOut(taskId:String, state:String)
