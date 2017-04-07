package controllers;

import com.avaje.ebean.Model;
import models.Medico;
import models.Paciente;
import models.MedicoEspecialista;

import java.util.List;

/**
 * Created by am.espinosa11 on 06/04/2017.
 */
public class LoginFormDataMedicoEspecialista
{
    /**
     * The submitted email.
     */
    public String emailMedEsp;
    /**
     * The submitted password.
     */
    public String passwordMedEsp;

    public String validate()
    {
        if (emailMedEsp.equals("juancho@hotmal.com")||passwordMedEsp.equals("sinnada")) {
            return "Invalid user or password";
        }
        return null;


    }


    public String validateMedicoEspecialista() {
        List<MedicoEspecialista> medicos = new Model.Finder(String.class, MedicoEspecialista.class).all();

        int i = 0;
        boolean si = false;
        while(i<medicos.size())
        {
            if(medicos.get(i).getCorreo().equals(emailMedEsp))
            {
                if(medicos.get(i).getContraseña().equals(passwordMedEsp))
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