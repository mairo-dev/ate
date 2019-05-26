package com.mairo.ate.messages

import com.mairo.ate.http.ImperativeRequestContext

sealed trait ApiLocalMessage extends Message

case class RequestMessage[T](
                           context: ImperativeRequestContext,
                           data: T
                         ) extends ApiLocalMessage
