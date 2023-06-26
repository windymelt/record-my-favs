package com.github.windymelt.recordmyfavs

import com.amazonaws.services.lambda.runtime.Context
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

import java.io.InputStream
import java.io.OutputStream

case class IftttPayload(
    text: String,
    name: String,
    perm: String,
    link1: String,
    createdAt: String,
    embed: String
)

object Main {
  def handler(in: InputStream, out: OutputStream, ctx: Context): Unit = {
    val payloadString = scala.io.Source.fromInputStream(in).mkString
    val iftttPayloadString = parse(payloadString)
      .map(
        _.hcursor
          .downField("body")
          .`as`[String]
      )
      .flatten
      .getOrElse("")

    val iftttPayload = decode[IftttPayload](iftttPayloadString)
    println(iftttPayload)
    out.write("{}".getBytes())
    out.flush()
    out.close()
    in.close()
  }
}
