package com.aleksandrbogomolov

import com.aleksandrbogomolov.configuration.SparkConfiguration
import com.aleksandrbogomolov.stream.FilterStream

object Main extends App {

  //    filters = req.getParameter("filters").split(" ")
  val filters: Array[String] = Array("#scala", "#java", "#groovy", "#kotlin")
  FilterStream.configuration = new SparkConfiguration
  FilterStream.startStream(filters)
}
