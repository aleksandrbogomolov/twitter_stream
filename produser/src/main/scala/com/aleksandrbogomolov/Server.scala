package com.aleksandrbogomolov

import com.aleksandrbogomolov.stream.FilterStream
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.{AbstractVerticle, Handler}

class Server extends AbstractVerticle {

  var stream: FilterStream = _

  override def start(): Unit = {
    vertx.deployVerticle(Publisher)
    vertx.createHttpServer.requestHandler(new Handler[HttpServerRequest] {
      override def handle(event: HttpServerRequest): Unit = event.path() match {
        case path if path.contains("start") =>
          val filters: Array[String] = event.getParam("filter").split(",")
          if (filters.length > 0) {
            stream = new FilterStream(filters)
            stream.startStream()
          }
        case path if path.contains("stop") => stream = null
      }
    }).listen(8888)
  }

  override def stop(): Unit = stream.stopStream()
}
