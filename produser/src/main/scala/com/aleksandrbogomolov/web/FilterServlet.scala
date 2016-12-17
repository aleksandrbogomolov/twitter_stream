package com.aleksandrbogomolov.web

import javax.servlet.annotation.WebServlet
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import com.aleksandrbogomolov.configuration.SparkConfiguration
import com.aleksandrbogomolov.stream.FilterStream

@WebServlet(urlPatterns = Array("/filter"), loadOnStartup = 1)
class FilterServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    //    filters = req.getParameter("filters").split(" ")
    val filters: Array[String] = Array("#scala", "#java", "#groovy", "#kotlin")
    req.getParameter("event") match {
      case "start" =>
        resp.getWriter.println("App work")
        FilterStream.configuration = new SparkConfiguration
        FilterStream.run(filters)
      case "stop" => FilterStream.stop()
    }
  }
}
