package controllers;



import com.avaje.ebean.Model;
import models.Paciente;
import play.data.Form;


import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import static play.libs.Json.toJson;

import models.*;

import views.html.index;
import views.html.vistaGeneralPaciente;
import views.html.loginPaciente;
import views.html.pacientesRegistrados;
import views.html.*;

import javax.annotation.CheckForSigned;


/**
 * Created by am.espinosa11 on 12/02/2017.
 */
//@With(Secure.class)
public class PacienteController extends Controller
{

    public Result pagPrincial()
    {
        return ok(vistaGeneralPaciente.render(""));
    }


    public Result login()
    {
        Form f = Form.form(LoginFormDataPaciente.class);
        return ok(loginPaciente.render());
    }

    public Result authenticar() {
        Form<LoginFormDataPaciente> loginFormPaic = Form.form(LoginFormDataPaciente.class).bindFromRequest();

        if (loginFormPaic.hasErrors()) {
            return badRequest(loginPaciente.render());
        } else {
            session().clear();
            session("emailPac", loginFormPaic.get().emailPac);
            flash("Ingresa"+loginFormPaic.get().emailPac);
            return ok(pacientesRegistrados.render(loginFormPaic.get().emailPac));

        }
    }

    public Result create()
    {
        Paciente paciente = Form.form(Paciente.class).bindFromRequest().get();
        paciente.save();
        return ok(toJson(paciente));
    }

    //@Security.Authenticated(Secured.class)
    public Result agregarMedico(Long idMedico, Long idPaciente)
    {
        Paciente paciente = Paciente.find.byId(idPaciente);
        Medico medico = Medico.find.byId(idMedico);
        paciente.setMedico(medico);
        medico.setPaciente(paciente);
        paciente.save();
        return ok(toJson(paciente));
    }


    //@Security.Authenticated(Secured.class)
    public Result read() {

        List<Paciente> pacientes = new Model.Finder(String.class, Paciente.class).all();
        String d = "";
        int i = 0;
        while(i<pacientes.size())
        {
            d += " Nombre: " + pacientes.get(i).getNombre();
            d += " Apellido: "+pacientes.get(i).getApellido();
            d+= " Correo: "+pacientes.get(i).getCorreo();
            d+= " Sexo: "+pacientes.get(i).getSexo();
            d+= " Edad: "+pacientes.get(i).getEdad();
            d+= " Estatura: "+ pacientes.get(i).getEstatura();
            i++;
        }
        return ok(medicosVerPacientes.render(d));
    }

    public Result readID() {

        List<Paciente> pacientes = new Model.Finder(String.class, Paciente.class).all();
        return ok(toJson(pacientes));
    }

    @Security.Authenticated(Secured.class)
    public Result delete(Long pId)
    {
        Paciente paciente = Paciente.find.byId(pId);
            paciente.delete();
        return ok(toJson(paciente));
    }

    @Security.Authenticated(Secured.class)
    public Result update(Long pId)
    {
        Paciente paciente = Form.form(Paciente.class).bindFromRequest().get();
        Paciente pS = Paciente.find.byId(pId);

        pS.setApellido(paciente.getApellido());
        pS.setNombre(paciente.getNombre());
        pS.setCorreo(paciente.getCorreo());
        pS.setEdad(paciente.getEdad());
        pS.setEstatura(paciente.getEstatura());
        pS.setPeso(paciente.getPeso());
        pS.setSexo(paciente.getSexo());
        pS.save();

        return ok(toJson(pS));
    }

    public Result getById(Long pId)
    {
        Paciente paciente = Paciente.find.byId(pId);
        return ok(toJson(paciente));
    }



    //public Result createRegistroDePaciente(Long pId)
    //{
        //Registro registro = Form.form(Registro.class).bindFromRequest().get();
      //  Paciente paciente = Paciente.find.byId(pId);
        //registro.save();
        //return ok(toJson(registro));
    //}

    //public Result readRegistrosDePaciente(Long pId) {
      //  List<Registro> registros = new Model.Finder(String.class, Registro.class).all();
       // Paciente paciente = Paciente.find.byId(pId);
        /*List<Registro> registrosPaciente = new ArrayList<Registro>();
        for(Registro registro:registros) {
            Paciente paciente = registro.getPaciente();
            Long idPaciente = paciente.getId();
            if (idPaciente == pId)
                registrosPaciente.add(registro);
        }*/

        //return ok(toJson(registros));
    //}

    @Security.Authenticated(Secured.class)
    public Result readRegistrosDePacienteRangoFechas(Long pId, Long f1, Long f2) {
        Paciente p = Paciente.find.byId(pId);
        List<Registro> registros = p.getRegistros();
        List<Registro> registrosPaciente = null;
        //for(Registro registro:registros) {
          //  Date fecha = registro.getFechaExpedicion();
        int i = 0;
        Date fecha1 = new Date(f1);
        Date fecha2 = new Date(f2);
        while(i<registros.size())
        {
            if(registros.get(i).getFechaExpedicion().after(fecha1))
            {
               registrosPaciente.add(registros.get(i));
            }
            i++;
        }

           // String v = "SI";
            //if(registros.get(1).getFechaExpedicion().after(fecha1))
            //{
              //  return ok(toJson(registros)+v);
            //}
            //if (fecha.after(fecha1) && fecha.before(fecha2) )
              //  registrosPaciente.add(registro);
        //}

        return ok(toJson(registrosPaciente));
    }

}
