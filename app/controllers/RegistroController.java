package controllers;


import com.avaje.ebean.Model;
import com.avaje.ebeaninternal.server.lib.util.Str;
import models.Paciente;
import models.Registro;
import models.Urgencia;
import play.data.Form;

import play.mvc.Controller;
import play.mvc.Result;
import scala.util.parsing.json.JSONArray;
import views.html.index;

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
        registro.save();
        //Paciente p = Paciente.find.byId(pId);
        //List<Registro> registros = new Model.Finder(String.class, Registro.class).all();
        //List<Registro> r = registros;
        //for(int i = 0; i<registros.size();i++)
        //{
          //  if(registros.get(i).getPaciente().getId()==p.getId())
            //{
              //  r.add(registros.get(i));

           // }
        //}
        //p.setRegistros(registros);
        //p.update();

        //p.agregarRegistro(registro);

        //if(p.getRegistros().isEmpty())
        //{
          //  p.inicializarRegistros(registro);
        //}
        //else {
          //  p.agregarRegistro(registro);
        //}
        if(registro.getColor(pId).equals("ROJO"))
        {
            Urgencia urgencia = Form.form(Urgencia.class).bindFromRequest().get();
            urgencia.save();
            //Urgencia nueva = Urgencia.find.byId(urgencia.getId());
            urgencia.setFecha(registro.getFechaExpedicion());
            urgencia.setPaciente(pId);
            urgencia.setDescripcion("El paciente presenta altos niveles");
            urgencia.save();
            return ok(toJson(registro)+"PACIENTE EN PELIGRO"+toJson(urgencia));
        }
        if(registro.getColor(pId).equals("AMARILLO"))
        {
            return ok(toJson(registro)+ "PACIENTE NECESITA CONSEJO MEDICO");
        }


        //System.out.println(registro);
        return ok(toJson(registro));
    }


    public Result read(Long pId) {

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
