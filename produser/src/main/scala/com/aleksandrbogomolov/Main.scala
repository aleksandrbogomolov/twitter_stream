package com.aleksandrbogomolov

import com.aleksandrbogomolov.configuration.SparkConfiguration
import com.aleksandrbogomolov.streams.FilterStream

object Main extends App {

  val filters: Array[String] = Array("#scala", "#java", "#groovy", "#kotlin")

  val configuration: SparkConfiguration = new SparkConfiguration

  val stream = new FilterStream(configuration, filters)

  stream.configuration.streamingContext.start()

  stream.configuration.streamingContext.awaitTermination()
}
