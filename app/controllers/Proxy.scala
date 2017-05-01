package controllers


import play.api.mvc.{Action, Controller}
import play.api.libs.ws.WS
import play.api.libs.concurrent.Execution.Implicits._
import play.api.Play.current

/**
  * Created by am.espinosa11 on 30/04/2017.
  */
class Proxy extends Controller
{
  def index(url: String) = Action.async {
    WS.url(url).get().map(resp => Ok(resp.body).as("text/html"))
  }
}
