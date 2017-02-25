package controllers

import controllers.dev.Controller
import play.api.mvc._
import repositories.dev.RepositoryComponentImpl
import services.dev.ServiceComponentImpl

object Application extends Controller
  with ServiceComponentImpl
  with RepositoryComponentImpl {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}