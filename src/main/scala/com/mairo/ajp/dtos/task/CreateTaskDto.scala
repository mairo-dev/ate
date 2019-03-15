package com.mairo.ajp.dtos.task

import java.time.ZonedDateTime

import com.mairo.ajp.dtos.general.TaskDescription

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 07.11.2018
  */


case class CreateTaskDtoIn(queue:String,
                          notBeforeTime:ZonedDateTime,
                          notAfterTime: ZonedDateTime,
                          call: TaskDescription,
                          callback: Option[TaskDescription])

case class CreateTaskDtoOut(taskId:String, state:String)
