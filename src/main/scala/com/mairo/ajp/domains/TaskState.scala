package com.mairo.ajp.domains

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 07.11.2018
  */


object TaskState extends Enumeration {

  private val ready = "READY"
  private val produced = "PRODUCED"
  private val consumed = "CONSUMED"
  private val started = "STARTED"
  private val completed = "COMPLETED"
  private val failed = "FAILED"
  private val canceled = "CANCELED"

  type TaskState = Value

  val Ready = Value(ready)
  val Produced = Value(produced)
  val Consumed = Value(consumed)
  val Started = Value(started)
  val Completed = Value(completed)
  val Failed = Value(failed)
  val Canceled = Value(canceled)

}


