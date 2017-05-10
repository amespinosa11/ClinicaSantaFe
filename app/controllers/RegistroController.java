package controllers;


import Seguridad.HashGenerator;
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
import views.html.*;
/**
 * Created by otalora on 12/02/2017.
 */
public class RegistroController extends Controller
{

    public Result create(Long pId)
    {
        Registro registro = Form.form(Registro.class).bindFromRequest().get();

        if(!hashCoinciden(registro))
        {
            return badRequest("Datos han sido corrompidos: hash no coincide con original");
        }


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


    public Result readID(Long idPaciente) {

        //List<Registro> registros = new Model.Finder(String.class, Registro.class).all();
        Paciente p = Paciente.find.byId(idPaciente);
        List<Registro>registros = p.getRegistros();
        return ok(toJson(registros));
    }

    public Result read(String email) {

        //List<Registro> registros = new Model.Finder(String.class, Registro.class).all();
        /*List<Paciente> pacientes = new Model.Finder(String.class, Paciente.class).all();

        int i = 0;
        Long id = 1L;
        while(i<pacientes.size())
        {
            if(pacientes.get(i).getCorreo().equals(email))
            {
                id = pacientes.get(i).getId();
            }
            i++;
        }
        Paciente p = Paciente.find.byId(id);
        */
        List<Registro> registros = new Model.Finder(String.class, Registro.class).all();
        //List<Tratamiento> tratamientos = p.getTratamientos();

        List<String> array = new ArrayList<>();

        for(int j = 0;j<registros.size();j++)
        {
            String d = "Frecuencia cardiaca: " + registros.get(j).getFrecuenciaCardiaca().toString() + "\n";
            d += " Actividad fisica: "+ registros.get(j).getNivelActividadFisica() + "\n";
            d += " Nivel Estres: "+ registros.get(j).getNivelEstres()+ "\n";
            d += " Presión sanguinea: " + registros.get(j).getPresionSanguinea1()+" - "+registros.get(j).getPresionSanguinea2()+ "\n";
            d += " Fecha: " + registros.get(j).getFechaExpedicion();
            d += " Color: " + registros.get(j).getColor(1L);

            array.add(d);
        }

        //List<Registro>registros = p.getRegistros();

       // return ok(toJson(registros));
        return ok(pacientesRegistros.render(array));
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

    /**
     * Verifica que el codigo hash enviado por parámetro sea el mismo al generado tras leer los datos.
     * Esto permite asegurar la integridad de los datos de los sensores del registro.
     * @param registro registro ingresado del que se quiere analizar la integridad
     * @return true si hash enviado y generado coinciden, false si no
     */
    private boolean hashCoinciden(Registro registro)
    {
        boolean coinciden;
        String mensajePreHash = registro.getFrecuenciaCardiaca() +"/" + registro.getPresionSanguinea1() + "/"
                + registro.getPresionSanguinea2() + "/" + registro.getNivelEstres();
        String hashGenerado = HashGenerator.generarHashSHA1(mensajePreHash.trim());
        coinciden = hashGenerado.equals(registro.getCodigoHash());


        return coinciden;


    }

}
