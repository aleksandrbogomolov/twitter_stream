package com.aleksandrbogomolov.stream

import com.aleksandrbogomolov.configuration.SparkConfiguration
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.twitter.TwitterUtils
import twitter4j.Status

class FilterStream(val configuration: SparkConfiguration, val filters: Array[String]) {

  val stream: DStream[Status] = TwitterUtils.createStream(configuration.streamingContext, configuration.auth, filters)

  val texts: DStream[String] = stream.map(s => s.getText)

  texts.print()
}
