package com.aleksandrbogomolov.stream

import com.aleksandrbogomolov.configuration.SparkConfiguration
import io.vertx.core.Vertx
import org.apache.log4j.BasicConfigurator
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.twitter.TwitterUtils
import twitter4j.Status

object FilterStream {

  var configuration: SparkConfiguration = _

  val vertx: Vertx = Vertx.vertx()

  BasicConfigurator.configure()

  def startStream(filters: Array[String]): Unit = {
    val stream: DStream[Status] = TwitterUtils.createStream(configuration.streamingContext, configuration.auth, filters)
    stream.map(s => s.getText).foreachRDD(rdd => rdd.foreach(vertx.eventBus().publish("tweet_feed", _)))
    configuration.streamingContext.start()
    configuration.streamingContext.awaitTermination()
  }

  def stopStream(): Unit = configuration.streamingContext.stop(stopSparkContext = false, stopGracefully = true)
}
