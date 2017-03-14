package controllers;


import com.avaje.ebean.Model;
import com.avaje.ebeaninternal.server.lib.util.Str;
import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.data.Form;

import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import scala.util.parsing.json.JSONArray;
import views.html.index;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.newArray;
import static play.libs.Json.newObject;
import static play.libs.Json.toJson;

/**
 * Created by otalora on 12/02/2017.
 */
public class RegistroController extends Controller
{

    public Result create(Long pId)
    {
        Registro registro = Form.form(Registro.class).bindFromRequest().get();

        registro.setPaciente(pId);
        Paciente p = Paciente.find.byId(pId);
        p.setRegistro(registro);

        if(registro.getColor(pId).equals("ROJO"))
        {
            Urgencia urgencia = new Urgencia(registro.getFechaExpedicion(),"El paciente presenta altos niveles");
            urgencia.setPaciente(pId);
            p.setUrgencia(urgencia);
            urgencia.save();
            p.save();
            registro.save();

            return ok(toJson(urgencia)+registro.getColor(pId));
        }
        if(registro.getColor(pId).equals("AMARILLO"))
        {

            Notificacion notificacion = new Notificacion("Consejo","El paciente necesita un consejo");
            //notificacion.setRegistro(registro);
            notificacion.setPaciente(p);
            notificacion.setFecha(registro.getFechaExpedicion());

            //int j = 0;
            //while(j<p.getMedicos().size())
            //{
            //  Medico medico = Medico.find.byId(p.getMedicos().get(j).getId());
            // medico.setNotificacion(notificacion);
            // medico.save();
            //j++;
            //}
            //registro.setNotificacion(notificacion);
            //Medico medico = Medico.find.byId(1L);
            //notificacion.setMedico(p.getMedicos().get(1));
            //p.getMedicos().get(0).setNotificacion(notificacion);
            notificacion.save();
            registro.save();
            p.save();

            return ok(toJson(notificacion)+registro.getColor(pId));
        }




        p.save();
        registro.save();

        return ok(toJson(registro)+registro.getColor(pId));

    }


    public Result read(Long idPaciente) {

        //List<Registro> registros = new Model.Finder(String.class, Registro.class).all();
        Paciente p = Paciente.find.byId(idPaciente);
        List<Registro>registros = p.getRegistros();
        return ok(toJson(registros));
    }

    public Result delete(Long pId)
    {
        Registro registro = Registro.find.byId(pId);
        registro.delete();
        return ok(toJson(registro));
    }

    public Result deleteMultiple(Long pId1,Long pId2)
    {
        while(pId1<pId2)
        {
            Registro registro = Registro.find.byId(pId1);
            registro.delete();
            pId1++;
        }

        Registro r = Registro.find.byId(pId2);
        return ok(toJson(r));
    }

    public Result update(Long pId)
    {
        Registro registro = Form.form(Registro.class).bindFromRequest().get();
        Registro pS = Registro.find.byId(pId);

        pS.setFrecuenciaCardiaca(registro.getFrecuenciaCardiaca());
        pS.setFechaExpedicion(registro.getFechaExpedicion());
        pS.setNivelActividadFisica(registro.getNivelActividadFisica());
        pS.setNivelEstres(registro.getNivelEstres());
        pS.setPresionSanguinea1(registro.getPresionSanguinea1());
        pS.setPresionSanguinea2(registro.getPresionSanguinea2());
        pS.save();

        return ok(toJson(pS));
    }

    public Result getById(Long pId,Long idPaciente)
    {
        Paciente p = Paciente.find.byId(idPaciente);
        Registro registro = Registro.find.byId(pId);

        return ok(toJson(registro));
    }

}
