package domain.dev

import domain.dev.DevStatus.{ConfigPropKey, ConfigPropValue}

case class DevStatus(
  deviceId: Long,
  config: Map[ConfigPropKey, ConfigPropValue]
) {
  def asCommands(target: Map[ConfigPropKey, ConfigPropValue]): Map[ConfigPropKey, ConfigPropValue] = {
    val diffConf = target.toSet.diff(this.config.toSet).toMap
    diffConf
  }
}

object DevStatus {
  case class ConfigPropKey(
    configIndex: Int,
    propIndex: Int
  )
  type ConfigPropValue = Int
}

