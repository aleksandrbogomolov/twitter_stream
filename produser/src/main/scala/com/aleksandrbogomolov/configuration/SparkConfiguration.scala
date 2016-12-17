package com.aleksandrbogomolov.configuration

import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import twitter4j.auth.{Authorization, AuthorizationFactory}
import twitter4j.conf.ConfigurationContext

class SparkConfiguration {

  val auth: Option[Authorization] = Option(AuthorizationFactory.getInstance(ConfigurationContext.getInstance))

  val sparkContext = new SparkContext(new SparkConf().setAppName("twitter_stream").setMaster("local[*]"))

  val streamingContext = new StreamingContext(sparkContext, Seconds(30))

  val rootLogger: Logger = Logger.getRootLogger

  rootLogger.setLevel(Level.ERROR)
}
