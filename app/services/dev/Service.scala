package services.dev

import domain.dev.DevConfigs
import repositories.dev.RepositoryComponent

trait ServiceComponent {
  val service: Service
  trait Service {
    def postStatus(d: DevConfigs): DevConfigs
    def postTarget(t: DevConfigs): DevConfigs
  }
}

trait ServiceComponentImpl extends ServiceComponent {
  self: RepositoryComponent =>
  override val service = new ServiceImpl
  class ServiceImpl extends Service {
    override def postStatus(s: DevConfigs): DevConfigs = {
      repository.updateDevStatus(s)
    }
    override def postTarget(t: DevConfigs): DevConfigs = {
      repository.updateDevTarget(t)
    }
  }
}
