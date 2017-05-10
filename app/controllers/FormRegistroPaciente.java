package controllers;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Created by am.espinosa11 on 03/05/2017.
 */
public class FormRegistroPaciente
{
    /**
     * Nombre del Paciente.
     */
    public String nombrePaciente;

    /**
     * Apellido del Paciente.
     */
    public String apellidoPaciente;

    public String correoPaciente;

    public Integer edadPaciente;

    public Double pesoPaciente;

    public Double estaturaPaciente;

    public String sexoPaciente;

    public String contraseñaPaciente;

    public String validate()
    {

        if(nombrePaciente.equals("nada"))
        {
            return "Falta un nombre";
        }

        return null;
    }

}
