package services.dev

import domain.dev.Status
import repositories.dev.RepositoryComponent

trait ServiceComponent {

    val service: Service

    trait Service {

        def postStatus(d: Status): Status

    }

}

trait ServiceComponentImpl extends ServiceComponent {

    self: RepositoryComponent =>

    override val service = new ServiceImpl

    class ServiceImpl extends Service {

        override def postStatus(d: Status): Status = {
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
