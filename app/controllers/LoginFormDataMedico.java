package controllers;

import com.avaje.ebean.Model;
import models.Medico;
import models.Paciente;

import java.util.List;

/**
 * Created by am.espinosa11 on 06/04/2017.
 */
public class LoginFormDataMedico
{
    /**
     * The submitted email.
     */
    public String emailMed;
    /**
     * The submitted password.
     */
    public String passwordMed;

    public String validate()
    {
        if (emailMed.equals("carlos@hotmal.com")||passwordMed.equals("sinnada")) {
            return "Invalid user or password";
        }
        return null;


    }


    public String validateMedico() {
        List<Medico> medicos = new Model.Finder(String.class, Medico.class).all();

        int i = 0;
        boolean si = false;
        while(i<medicos.size())
        {
            if(medicos.get(i).getCorreo().equals(emailMed))
            {
                if(medicos.get(i).getContraseña().equals(passwordMed))
                {
                    si =true;
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
