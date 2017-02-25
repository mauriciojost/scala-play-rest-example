package repositories.dev

import java.util.concurrent.ConcurrentHashMap

import domain.dev.DevStatus

trait RepositoryComponent {
  val repository: Repository

  trait Repository {
    def updateDevStat(user: DevStatus): DevStatus
  }

}

trait RepositoryComponentImpl extends RepositoryComponent {
  override val repository = new RepositoryImpl

  class RepositoryImpl extends Repository {

    val devicesStatus = new ConcurrentHashMap[Long, DevStatus]
    val devicesTarget = new ConcurrentHashMap[Long, DevStatus]

    override def updateDevStat(currentDevStatus: DevStatus): DevStatus = {
      val devId = currentDevStatus.deviceId
      val targetDevStatus = devicesTarget.getOrDefault(currentDevStatus.deviceId, DevStatus(devId, Map.empty))
      devicesStatus.put(currentDevStatus.deviceId, currentDevStatus)
      val commands = currentDevStatus.asCommands(targetDevStatus.config)
      DevStatus(devId, commands)
    }

  }

}