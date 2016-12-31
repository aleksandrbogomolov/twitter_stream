package com.aleksandrbogomolov

import com.fasterxml.jackson.databind.ObjectMapper
import io.vertx.core.AbstractVerticle
import twitter4j.Status

object Publisher extends AbstractVerticle {

  val mapper: ObjectMapper = new ObjectMapper()

  def publish(str: Status): Unit = {
    val msg = mapper.writeValueAsString(str)
    println("------------------------------")
    println(msg)
    vertx.eventBus().publish("tweet_feed", msg)
  }
}
