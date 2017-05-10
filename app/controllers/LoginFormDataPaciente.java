package controllers;

import com.avaje.ebean.Model;
import models.Paciente;

import java.util.List;

/**
 * Created by am.espinosa11 on 05/04/2017.
 */
public class LoginFormDataPaciente {
    /**
     * The submitted email.
     */
    public String emailPac;
    /**
     * The submitted password.
     */
    public String passwordPac;

    public Long id;

    public String validate()
    {
       if(emailPac.equals("ana"))
       {
           return "Mal";
       }
       else
       {
           return null;
       }

    }


    public String validatePaciente() {
        List<Paciente> pacientes = new Model.Finder(String.class, Paciente.class).all();
        Paciente p = null;
        int i = 0;
        boolean si = false;
        while(i<pacientes.size())
        {
            if(pacientes.get(i).getCorreo().equals(emailPac))
            {
                if(pacientes.get(i).getContraseña().equals(passwordPac))
                {
                    p= pacientes.get(i);
                }
            }
            i++;
        }
        if(p == null)
        {
            return "Invalid user or password";
        }
        else
        return null;

    }
}