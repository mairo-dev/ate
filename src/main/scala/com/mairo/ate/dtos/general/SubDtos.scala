package com.mairo.ate.dtos.general

import java.time.ZonedDateTime

import com.mairo.ate.domains.TaskState.TaskState
import spray.json.JsValue

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 07.11.2018
  */

case class TaskDescription(uri: String,
                           method: String,
                           headers: Option[Map[String, String]],
                           data: Option[JsValue])

case class TaskData(id:Long,
                    created:ZonedDateTime,
                    validTill:ZonedDateTime,
                    state: TaskState)