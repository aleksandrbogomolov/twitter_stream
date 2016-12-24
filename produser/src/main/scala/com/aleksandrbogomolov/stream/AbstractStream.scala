package com.aleksandrbogomolov.stream

import com.aleksandrbogomolov.configuration.SparkConfiguration
import org.apache.spark.streaming.dstream.DStream
import twitter4j.Status

trait AbstractStream {

  val configuration: SparkConfiguration =  new SparkConfiguration

  def startStream(): DStream[Status]

  def stopStream(): Unit
}
