package controllers;

import models.Paciente;

import play.data.validation.ValidationError;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by am.espinosa11 on 05/04/2017.
 */
public class LoginFormData
{
    /** The submitted email. */
    public String email  ;
    /** The submitted password. */
    public String password;

    public String validate()
    {
        if (email.equals("ana@hotmal.com")) {
            return "Invalid user or password";
        }
        return null;

    }


}
