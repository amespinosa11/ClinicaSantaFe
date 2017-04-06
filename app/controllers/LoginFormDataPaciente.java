package controllers;

import com.avaje.ebean.Model;
import models.Paciente;

import java.util.List;

/**
 * Created by am.espinosa11 on 05/04/2017.
 */
public class LoginFormDataPaciente
{
    /** The submitted email. */
    public String emailPac  ;
    /** The submitted password. */
    public String passwordPac;

    public String validate() {
        List<Paciente> pacientes = new Model.Finder(String.class, Paciente.class).all();
        int i = 0;
        boolean si = false;
        while(i<pacientes.size()&&pacientes!=null)
        {
            if(pacientes.get(i).getCorreo().equals(emailPac))
            {
                if(pacientes.get(i).getContraseña().equals(passwordPac))
                {
                    si=true;
                }
            }
            i++;
        }
        if(!si)
        {
            return "Invalid user or password";
        }
        return null;
    }

}
