package repositories.dev

import domain.dev.DevStat
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

trait DevStatRepositoryComponent {
    val devStatRepository: UserRepository
    
    trait UserRepository {
        
        def updateDevStat(user: DevStat): DevStat

        /*
        def updateUser(user: User)
        
        def tryFindById(id: Long): Option[User]

        def tryFindByEmail(email: String): Option[User]
        
        def delete(id: Long)
        */
        
    }
}

trait DevStatRepositoryComponentImpl extends DevStatRepositoryComponent {
    override val devStatRepository = new UserRepositoryImpl
    
    class UserRepositoryImpl extends UserRepository {
        
        val users = new ConcurrentHashMap[Long, DevStat]
        val idSequence = new AtomicLong(0)
        
        override def updateDevStat(user: DevStat): DevStat = {
            val newId = idSequence.incrementAndGet()
            val createdUser = user.copy(id = Option(newId))
            users.put(newId, createdUser)
            createdUser
        }

        /*
        override def updateUser(user: User) {
            users.put(user.id.get, user)
        }
        
        override def tryFindById(id: Long): Option[User] = {
            Option(users.get(id))
        }

        override def tryFindByEmail(email: String): Option[User] = {
            import scala.collection.JavaConversions._
            users.values().find(_.email == email)
        }
        
        override def delete(id: Long) {
            users.remove(id)
        }
        */
        
    }
    
}