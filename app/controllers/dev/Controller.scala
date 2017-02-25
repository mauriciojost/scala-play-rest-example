package controllers.dev

import play.api.libs.iteratee.Enumerator
import play.api.mvc.{Controller => PlayController, ResponseHeader, HttpConnection, Result, Action}
import services.dev.ServiceComponent

trait Controller extends PlayController {
    self: ServiceComponent =>0

    def updateDevStat(id: Long) = Action(parse.urlFormEncoded) {request =>
        println(request.body)
        Result(header = ResponseHeader(OK), body = Enumerator("MAURI".getBytes), connection = HttpConnection.Close)
    }

}

case class Resource(email: String)
