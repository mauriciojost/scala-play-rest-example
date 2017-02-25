package repositories.dev

import domain.dev.Status
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

trait RepositoryComponent {
    val repository: Repository

    trait Repository {
        def updateDevStat(user: Status): Status
    }
}

trait RepositoryComponentImpl extends RepositoryComponent {
    override val repository = new RepositoryImpl

    class RepositoryImpl extends Repository {
        
        val users = new ConcurrentHashMap[Long, Status]
        val idSequence = new AtomicLong(0)
        
        override def updateDevStat(user: Status): Status = {
            val newId = idSequence.incrementAndGet()
            val createdUser = user.copy(device = Option(newId))
            users.put(newId, createdUser)
            createdUser
        }

    }
    
}