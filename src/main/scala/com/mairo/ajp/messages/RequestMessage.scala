package com.mairo.ajp.messages

import com.mairo.ajp.http.ImperativeRequestContext

sealed trait ApiLocalMessage extends Message

case class RequestMessage[T](
                           context: ImperativeRequestContext,
                           data: T
                         ) extends ApiLocalMessage
