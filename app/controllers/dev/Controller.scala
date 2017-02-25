package controllers.dev

import domain.dev.DevConfigs
import play.api.Logger
import play.api.libs.iteratee.Enumerator
import play.api.mvc.{Action, Controller => PlayController, HttpConnection, ResponseHeader, Result}
import services.dev.ServiceComponent

trait Controller extends PlayController {
  self: ServiceComponent => 0

  def updateDevStatus(devId: Long) = Action(parse.urlFormEncoded) { request =>
    val status = DevConfigs.fromBody(devId, request.body)
    val commandsToTarget = service.postStatus(status)
    Logger.info(s"Update $devId status: $status (commands: $commandsToTarget)")
    Result(
      header = ResponseHeader(OK),
      body = Enumerator(commandsToTarget.toUrlParams.getBytes),
      connection = HttpConnection.Close
    )
  }

  def updateDevTarget(devId: Long) = Action(parse.urlFormEncoded) { request =>
    val target = DevConfigs.fromBody(devId, request.body)
    val commandsToTarget = service.postTarget(target)
    Logger.info(s"Update $devId target: $target")
    Result(
      header = ResponseHeader(OK),
      body = Enumerator.empty[Array[Byte]],
      connection = HttpConnection.Close
    )
  }

}

