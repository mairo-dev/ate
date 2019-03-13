package com.mairo.ajp.domains

import com.mairo.ajp.domains.TaskState.TaskState
import spray.json.JsValue

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 07.11.2018
  */

case class Task(uri: String,
                method: String,
                headers: Option[Map[String, String]],
                data: Option[JsValue],
                state: TaskState)
