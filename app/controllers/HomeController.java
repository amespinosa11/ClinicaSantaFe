package controllers;

import play.data.Form;
import play.mvc.*;
import controllers.*;
import views.html.*;
import views.html.helper.form;
import play.data.*;

import javax.annotation.CheckForSigned;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */

    public Result index() {
        return ok(index.render(""));
    }

    public Result nosotros() {
        return ok(nosotros.render(""));
    }

    public Result servicios() {
        return ok(nuestrosServicios.render(""));
    }

    public Result nuestrosMedicos() {
        return ok(nuestrosMedicos.render(""));
    }

    public Result pagAlterna()
    {
        return ok(index.render("NO AUTORIZADO"));
    }


    public Result login()
    {
        Form f = Form.form(LoginFormData.class);
        return ok(login.render());
    }

    public Result authenticate() {
        Form<LoginFormData> loginForm = Form.form(LoginFormData.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render());
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.HomeController.login()
            );
        }
    }


}
