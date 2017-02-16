package controllers;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Paciente;
import play.data.Form;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import static play.libs.Json.toJson;

import models.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
/**
 * Created by am.espinosa11 on 12/02/2017.
 */
public class PacienteController extends Controller
{

    public Result create()
    {
        Paciente paciente = Form.form(Paciente.class).bindFromRequest().get();
        paciente.save();
        return ok(toJson(paciente));
    }

    public Result read() {

        List<Paciente> pacientes = new Model.Finder(String.class, Paciente.class).all();
        return ok(toJson(pacientes));
    }

    public Result delete(Long pId)
    {
        Paciente paciente = Paciente.find.byId(pId);
            paciente.delete();
        return ok(toJson(paciente));
    }

    public Result update(Long pId)
    {
        Paciente paciente = Form.form(Paciente.class).bindFromRequest().get();
        Paciente pS = Paciente.find.byId(pId);

        pS.setApellido(paciente.getApellido());
        pS.setNombre(paciente.getNombre());
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

    public Result createRegistroDePaciente(Long pId)
    {
        Registro registro = Form.form(Registro.class).bindFromRequest().get();
        Paciente paciente = Paciente.find.byId(pId);
        paciente.agregarRegistro(registro);
        registro.save();
        return ok(toJson(registro));
    }

    public Result readRegistrosDePaciente(Long pId) {
        List<Registro> registros = new Model.Finder(String.class, Registro.class).all();
        List<Registro> registrosPaciente = new ArrayList<Registro>();
        /*for(Registro registro:registros) {
            Paciente paciente = registro.getPaciente();
            Long idPaciente = paciente.getId();
            if (idPaciente == pId)
                registrosPaciente.add(registro);
        }*/

        return ok(toJson(registros));
    }

}
