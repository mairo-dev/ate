package com.mairo.ajp.http

import java.text.SimpleDateFormat
import java.time.ZonedDateTime

import spray.json.{JsString, JsValue, JsonFormat}
import spray.json._

import scala.util.Try

object DateMarshalling {

  import java.time.format.DateTimeFormatter

  lazy val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

  implicit object DateFormat extends JsonFormat[ZonedDateTime] {
    def write(date: ZonedDateTime) = JsString(dateToIsoString(date))
    def read(json: JsValue) = json match {
      case JsString(rawDate) =>
        parseIsoDateString(rawDate)
          .fold(deserializationError(s"Expected ISO Date format, got $rawDate"))(identity)
      case error => deserializationError(s"Expected JsString, got $error")
    }
  }


  private def dateToIsoString(date: ZonedDateTime) = date.format(formatter)

  private def parseIsoDateString(date: String): Option[ZonedDateTime] =
    Try{ ZonedDateTime.parse(date, formatter) }.toOption
}