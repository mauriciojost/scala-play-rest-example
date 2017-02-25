package repositories.dev

import java.util.concurrent.ConcurrentHashMap

import domain.dev.DevConfigs

trait RepositoryComponent {
  val repository: Repository

  trait Repository {
    def updateDevStatus(s: DevConfigs): DevConfigs
    def updateDevTarget(t: DevConfigs): DevConfigs
  }

}

trait RepositoryComponentImpl extends RepositoryComponent {
  override val repository = new RepositoryImpl

  class RepositoryImpl extends Repository {

    val devicesStatus = new ConcurrentHashMap[Long, DevConfigs]
    val devicesTarget = new ConcurrentHashMap[Long, DevConfigs]

    override def updateDevStatus(currentDevStatus: DevConfigs): DevConfigs = {
      val devId = currentDevStatus.deviceId
      devicesStatus.put(devId, currentDevStatus)

      val targetDevStatus = devicesTarget.getOrDefault(devId, DevConfigs(devId, Map.empty))
      val commandsToTarget = currentDevStatus.diff(targetDevStatus.config)
      DevConfigs(devId, commandsToTarget)
    }

    override def updateDevTarget(devTarget: DevConfigs): DevConfigs = {
      val devId = devTarget.deviceId
      devicesTarget.put(devId, devTarget)
    }

  }

}