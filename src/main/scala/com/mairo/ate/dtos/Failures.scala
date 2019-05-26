package com.mairo.ate.dtos

/**
  * @author Roman Maiun  roman.maiun@unicorn.com
  * @since 08.11.2018
  */

trait Failure
sealed trait ApiFailure extends Failure

case class FailureMessage(status: Int, message: String) extends ApiFailure
