package com.aleksandrbogomolov.stream

import com.aleksandrbogomolov.Publisher
import com.aleksandrbogomolov.configuration.SparkConfiguration
import org.apache.spark.streaming.twitter.TwitterUtils

class FilterStream(val filters: Array[String]) {

  val configuration: SparkConfiguration =  new SparkConfiguration

  def startStream(): Unit = {
    val statuses = TwitterUtils.createStream(configuration.streamingContext, configuration.auth, filters)
    statuses.foreachRDD(_.foreachPartition(_.foreach(Publisher.publish)))
    configuration.streamingContext.start()
    configuration.streamingContext.awaitTermination()
  }

  def stopStream(): Unit = {
    configuration.streamingContext.stop(stopSparkContext = true, stopGracefully = true)
  }
}
