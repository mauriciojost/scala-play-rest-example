package controllers.dev

import play.api.mvc._
import services.dev.DevStatServiceComponent

trait DevStatController extends Controller {
    self: DevStatServiceComponent =>0

    /*
    def emailAlreadyExists(implicit reads: Reads[String]) =
        Reads[String](js => reads.reads(js).flatMap { e =>
          userService.tryFindByEmail(e).map(_ => JsError("error.custom.emailAlreadyExists")).getOrElse(JsSuccess(e))
        })

    implicit val userReads = (__ \ "email").read[String](email andKeep emailAlreadyExists)
                                           .map(resource => UserResource(resource))
    */

    /*
    implicit val userWrites = new Writes[User] {
        override def writes(user: User): JsValue = {
            Json.obj(
                "id" -> user.id,
                "email" -> user.email
            )
        }
    }
    */

    /*
    def createUser = Action(parse.urlFormEncoded) {request =>
        println(request.body)
        Created
        /*
        unmarshalJsValue(request) { resource: UserResource =>
            val user = User(Option.empty, resource.email)
            userService.createUser(user)
            Created
        }
        */
    }
    */

    def updateDevStat(id: Long) = Action(parse.urlFormEncoded) {request =>
        println(request.body)
        /*
        unmarshalJsValue(request) { resource: UserResource =>
            val user = User(Option(id), resource.email)
            userService.updateUser(user)
            NoContent
        }
        */
        NoContent
    }

    /*
    def findUserById(id: Long) = Action {
        val user = userService.tryFindById(id)
        if (user.isDefined) {
            Ok(Json.toJson(user))
        } else {
            NotFound
        }
    }
    
    def deleteUser(id: Long) = Action {
        userService.delete(id)
        NoContent
    }

    def unmarshalJsValue[R](request: Request[JsValue])(block: R => Result)(implicit rds : Reads[R]): Result =
        request.body.validate[R](rds).fold(
            valid = block,
            invalid = e => {
                val error = e.mkString
                Logger.error(error)
                BadRequest(error)
            }
        )
       */

}

case class DevStatResource(email: String)
