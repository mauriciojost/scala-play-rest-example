package domain.dev

import domain.dev.DevConfigs.{ConfigPropKey, ConfigPropMap}
import scala.util.{Failure, Success, Try}
import play.api.Logger


/**
  * This class represents a device status at a given point in time.
 *
  * @param deviceId the devide the status is associated to
  * @param config the set of properties associated to the device
  */

case class DevConfigs(
  deviceId: Long,
  config: ConfigPropMap
) {
  def diff(target: ConfigPropMap): ConfigPropMap = {
    target.toSet.diff(this.config.toSet).toMap
  }

  def toUrlParams() = this.config.map {
    case (ConfigPropKey(ci, pi), v) => s"c${ci}p${pi}=${v}"
  }.mkString("&")

}

object DevConfigs {

  type ConfigPropMap = Map[ConfigPropKey, ConfigPropValue]
  type ConfigPropValue = Int
  val keyRegex = """c([\d]+)p([\d]+)""".r

  /**
    * It represents the key to the minimal unit of configuration.
 *
    * @param configIndex configurable index (normally a module in a device, like a particular sensor)
    * @param propIndex property index (normally a parameter for the module in the device, like the threshold for the sensor)
    */
  case class ConfigPropKey(
    configIndex: Int,
    propIndex: Int
  )

  def fromBody(devId: Long, body: Map[String, Seq[String]]): DevConfigs = {
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
    DevConfigs(
      deviceId = devId,
      config = devStatuses.toMap
    )
  }


}

