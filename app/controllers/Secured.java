package controllers;

import models.Paciente;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Context;


/**
 * Created by am.espinosa11 on 05/04/2017.
 */
public class Secured extends Security.Authenticator
{

    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }


    public Result onUnauthorized(Context context) {
        return redirect(routes.HomeController.index());
    }


    public static String getUser(Context ctx) {
        return ctx.session().get("email");
    }


    public static boolean isLoggedIn(Context ctx) {
        return (getUser(ctx) != null);
    }


    public static Paciente getUserInfo(Context ctx)
    {
        return (isLoggedIn(ctx) ? Paciente.findbyEmail.ref(getUser(ctx)) : null);
    }
}
