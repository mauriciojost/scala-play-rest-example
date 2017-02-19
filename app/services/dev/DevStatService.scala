package services.dev

import domain.dev.DevStat
import repositories.dev.DevStatRepositoryComponent

trait DevStatServiceComponent {

    val devStatService: DevStatService

    trait DevStatService {

        def updateDevStat(d: DevStat): DevStat

        /*
        def updateUser(user: User)

        def tryFindById(id: Long): Option[User]

        def tryFindByEmail(email: String): Option[User]

        def delete(id: Long)
        */

    }

}

trait DevStatServiceComponentImpl extends DevStatServiceComponent {
    self: DevStatRepositoryComponent =>

    override val devStatService = new DevStatServiceImpl

    class DevStatServiceImpl extends DevStatService {

        override def updateDevStat(d: DevStat): DevStat = {
            devStatRepository.updateDevStat(d)
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
