package controllers;


import com.avaje.ebean.Model;
import models.Paciente;
import models.Registro;
import play.data.Form;

import play.mvc.Controller;
import play.mvc.Result;
import scala.util.parsing.json.JSONArray;
import views.html.index;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by otalora on 12/02/2017.
 */
public class RegistroController extends Controller
{

    public Result create(Long pId)
    {
        Registro registro = Form.form(Registro.class).bindFromRequest().get();
        registro.save();
        registro.setPaciente(pId);
        //Paciente p = Paciente.find.byId(pId);
        //if(p.getRegistros().isEmpty())
        //{
          //  p.inicializarRegistros(registro);
        //}
        //else {
          //  p.agregarRegistro(registro);
        //}
        if(registro.getColor(pId).equals("ROJO"))
        {
            return ok(toJson(registro)+"PACIENTE EN PELIGRO");

            //Urgencia urgencia = Form.form(Urgencia.class).bindFromRequest().get();
            //urgencia.setFecha(registro.getFecha());
            //urgencia.setPaciente(pId);
            //urgencia.setDescripcion("NIVELES MUY ALTOS.PELIGRO PARA EL PACIENTE");
            //urgencia.save();

        }
        if(registro.getColor(pId).equals("AMARILLO"))
        {
            return ok(toJson(registro)+ "PACIENTE NECESITA CONSEJO MEDICO");
        }


        //System.out.println(registro);
        return ok(toJson(registro));
    }


    public Result read() {

        List<Registro> registros = new Model.Finder(String.class, Registro.class).all();
        return ok(toJson(registros));
    }

    public Result delete(Long pId)
    {
        Registro registro = Registro.find.byId(pId);
        registro.delete();
        return ok(toJson(registro));
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

    public Result getById(Long pId)
    {
        Registro registro = Registro.find.byId(pId);
        return ok(toJson(registro));
    }

}
