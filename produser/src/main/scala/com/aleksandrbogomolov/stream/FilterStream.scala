package com.aleksandrbogomolov.stream

import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.twitter.TwitterUtils
import twitter4j.Status

class FilterStream(val filters: Array[String]) extends AbstractStream {

  override def startStream(): DStream[Status] = {
    TwitterUtils.createStream(configuration.streamingContext, configuration.auth, filters)
  }

  override def stopStream(): Unit = configuration.streamingContext.stop(stopSparkContext = false, stopGracefully = true)
}
