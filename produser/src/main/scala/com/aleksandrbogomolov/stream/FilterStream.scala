package com.aleksandrbogomolov.stream

import com.aleksandrbogomolov.configuration.SparkConfiguration
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.twitter.TwitterUtils
import twitter4j.Status

object FilterStream {

  var configuration: SparkConfiguration = _

  def run(filters: Array[String]): Unit = {
    val stream: DStream[Status] = TwitterUtils.createStream(configuration.streamingContext, configuration.auth, filters)
    val texts: DStream[String] = stream.map(s => s.getText)
    texts.print()
    configuration.streamingContext.start()
    configuration.streamingContext.awaitTermination()
  }

  def stop(): Unit = configuration.streamingContext.stop(stopSparkContext = false, stopGracefully = true)
}
