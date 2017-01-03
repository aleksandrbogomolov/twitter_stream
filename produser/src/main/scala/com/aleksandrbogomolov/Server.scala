package com.aleksandrbogomolov

import com.aleksandrbogomolov.stream.{AbstractStream, FilterStream}
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.{AbstractVerticle, Handler}
import org.apache.spark.streaming.dstream.DStream
import twitter4j.Status

class Server extends AbstractVerticle {

  var stream: AbstractStream = _

  override def start(): Unit = {
    vertx.deployVerticle(Publisher)
    vertx.createHttpServer.requestHandler(new Handler[HttpServerRequest] {
      override def handle(event: HttpServerRequest): Unit = event.path() match {
        case path if path.contains("start") =>
          val filters: Array[String] = event.getParam("filter").split(",")
          if (filters != null) {
            stream = new FilterStream(filters)
            val statuses: DStream[Status] = stream.startStream()
            statuses.foreachRDD(_.foreachPartition(_.foreach(Publisher.publish)))
            stream.configuration.streamingContext.start()
            stream.configuration.streamingContext.awaitTermination()
          }
        case path if path.contains("stop") => stream.stopStream()
      }
    }).listen(8888)
  }

  override def stop(): Unit = stream.stopStream()
}
