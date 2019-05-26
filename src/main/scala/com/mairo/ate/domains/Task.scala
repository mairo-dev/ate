package com.mairo.ate.domains

import java.time.ZonedDateTime

import com.mairo.ate.domains.TaskState.TaskState
import com.mairo.ate.dtos.general.TaskDescription
import spray.json.JsValue

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 07.11.2018
  */

case class Task(id:Long,
                call: TaskDescription,
                callback: TaskDescription,
                queue:String,
                data: Option[JsValue],
                state: TaskState,
                notBeforeTime:ZonedDateTime,
                notAfterTime: ZonedDateTime,
                retries: Retries)

case class Retries(current:Int,
                   max:Int,
                   delays:List[Long])

