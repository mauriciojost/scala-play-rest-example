package controllers

import controllers.dev.DevStatController
import play.api._
import play.api.mvc._
import repositories.dev.DevStatRepositoryComponentImpl
import services.dev.DevStatServiceComponentImpl

object Application extends DevStatController
                   with DevStatServiceComponentImpl
                   with DevStatRepositoryComponentImpl {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}