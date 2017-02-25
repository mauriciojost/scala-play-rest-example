package controllers.dev

import domain.dev.DevStatus
import domain.dev.DevStatus.ConfigPropKey
import play.api.Logger
import play.api.libs.iteratee.Enumerator
import play.api.mvc.{Action, Controller => PlayController, HttpConnection, ResponseHeader, Result}
import services.dev.ServiceComponent

import scala.util.{Failure, Success, Try}

trait Controller extends PlayController {
  self: ServiceComponent =>
  0

  val keyRegex = """c([\d]+)p([\d]+)""".r

  def updateDevStat(devId: Long) = Action(parse.urlFormEncoded) { request =>
    val status = parseStatus(devId, request.body)
    val toTargetStatusCommands = service.postStatus(status)
    Logger.info(s"Update $devId status: $status (commands: $toTargetStatusCommands)")
    Result(
      header = ResponseHeader(OK),
      body = Enumerator(toUrlParams(toTargetStatusCommands).getBytes),
      connection = HttpConnection.Close
    )
  }

  private def parseStatus(devId: Long, body: Map[String, Seq[String]]): DevStatus = {

    // Valid examples are:
    // - "c0p0" -> 10
    // - "c1p2" -> 5
    val devStatusUnits = body.map {
      case (keyRegex(configIndex, propIndex), values) =>
        Try((ConfigPropKey(configIndex.toInt, propIndex.toInt), values.head.toInt))
      case unparseable =>
        Failure(new IllegalArgumentException(s"Cannot parse $unparseable"))
    }

    val devStatuses = devStatusUnits.flatMap {
      case Success(s) =>
        Some(s)
      case Failure(f) =>
        Logger.error(f.getMessage)
        None
    }.toSeq

    DevStatus(
      deviceId = devId,
      config = devStatuses.toMap
    )

  }

  def toUrlParams(devStatus: DevStatus): String = {
    devStatus.config.map {
      case (ConfigPropKey(ci, pi), v) => s"c${ci}p${pi}=${v}"
    }.mkString("&")
  }

}

