package com.aleksandrbogomolov

import com.google.gson.Gson
import io.vertx.core.AbstractVerticle
import twitter4j.Status

object Bus extends AbstractVerticle {

  def eb(str: Status): Unit = {
    val msg = new Gson().toJson(str)
    println("------------------------------")
    println(msg)
    vertx.eventBus().publish("tweet_feed", msg)
  }
}
