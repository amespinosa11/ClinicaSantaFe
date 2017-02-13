package controllers;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Paciente;
import play.data.Form;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by am.espinosa11 on 12/02/2017.
 */
public class PacienteController extends Controller
{

    public Result create()
    {
        JsonNode j = Controller.request().body().asJson();
        Paciente paciente = Paciente.bind(j);
        paciente.save();
        //return ok(Json.toJson(paciente));
        return redirect(routes.HomeController.index());
    }

    public Result read() {
        List<Paciente> pacientes = new Model.Finder<>(String.class,Paciente.class).all();
        return ok(Json.toJson(pacientes));
    }
}
