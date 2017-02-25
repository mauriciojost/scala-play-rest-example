package services.dev

import domain.dev.DevStatus
import repositories.dev.RepositoryComponent

trait ServiceComponent {

    val service: Service

    trait Service {

        def postStatus(d: DevStatus): DevStatus

    }

}

trait ServiceComponentImpl extends ServiceComponent {

    self: RepositoryComponent =>

    override val service = new ServiceImpl

    class ServiceImpl extends Service {

        override def postStatus(d: DevStatus): DevStatus = {
            repository.updateDevStat(d)
        }

        /*
        override def updateUser(user: User) {
            userRepository.updateUser(user)
        }
        
        override def tryFindById(id: Long): Option[User] = {
            userRepository.tryFindById(id)
        }

        override def tryFindByEmail(email: String): Option[User] = {
            userRepository.tryFindByEmail(email)
        }
        
        override def delete(id: Long) {
            userRepository.delete(id)
        }
        */
        
    }
}
