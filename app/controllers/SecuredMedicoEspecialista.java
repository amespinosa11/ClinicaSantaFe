package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import static play.mvc.Results.redirect;

/**
 * Created by am.espinosa11 on 06/04/2017.
 */
public class SecuredMedicoEspecialista extends Security.Authenticator
{
    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("emailMedEsp");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx)
    {
        return redirect(routes.MedicoEspecialistaController.login());
    }
}
