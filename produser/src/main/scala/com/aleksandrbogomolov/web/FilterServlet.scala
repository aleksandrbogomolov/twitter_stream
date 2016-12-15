package com.aleksandrbogomolov.web

import javax.servlet.annotation.WebServlet
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import com.aleksandrbogomolov.configuration.SparkConfiguration
import com.aleksandrbogomolov.stream.FilterStream

@WebServlet(urlPatterns = Array("/filter"), loadOnStartup = 1)
class FilterServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
//    val filters: Array[String] = req.getParameter("filters").split(" ")
    val filters: Array[String] = Array("#scala", "#java", "#groovy", "#kotlin")
    val configuration: SparkConfiguration = new SparkConfiguration
    val stream = new FilterStream(configuration, filters)
    stream.configuration.streamingContext.start()
    stream.configuration.streamingContext.awaitTermination()
  }
}
