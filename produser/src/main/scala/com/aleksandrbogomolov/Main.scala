package com.aleksandrbogomolov

import com.aleksandrbogomolov.stream.{AbstractStream, FilterStream}
import io.vertx.core.AbstractVerticle
import org.apache.spark.streaming.dstream.DStream
import twitter4j.Status

class Main extends AbstractVerticle {

  val filters: Array[String] = Array("#scala", "#java", "#groovy", "#kotlin")

  var stream: AbstractStream = _

  override def start(): Unit = {
    stream = new FilterStream(filters)
    vertx.deployVerticle(Bus)
    val statuses: DStream[Status] = stream.startStream()
    statuses.foreachRDD(_.foreachPartition(_.foreach(Bus.eb)))
    stream.configuration.streamingContext.start()
    stream.configuration.streamingContext.awaitTermination()
  }

  override def stop(): Unit = stream.stopStream()
}
