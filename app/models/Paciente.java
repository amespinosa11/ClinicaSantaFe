package models;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.With;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;

/**
 * Created by am.espinosa11 on 12/02/2017.
 */

@Entity
@Table(name = "paciente")
public class Paciente extends Model
{

    /**
     * id del Paciente.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del Paciente.
     */
    private String nombre;

    /**
     * Apellido del Paciente.
     */
    private String apellido;

    private String correo;

    private Integer edad;

    private Double peso;

    private Double estatura;

    private String sexo;

    @JsonBackReference
    private String contraseña;

    @OneToMany(mappedBy = "paciente")@JsonBackReference
    private List<Registro> registros;

    @OneToOne @JsonBackReference
    private Marcapaso marcapaso;


    @OneToMany(mappedBy = "paciente")@JsonBackReference
    private List<Urgencia> urgencias;

    @ManyToMany
    private List<Medico> medicos;

    @ManyToMany @JsonBackReference
    private List<MedicoEspecialista> medicosEspecialistas;

    @OneToMany(mappedBy = "paciente")@JsonBackReference
    private List<Diagnostico> diagnosticos;

    @OneToMany(mappedBy = "paciente")@JsonBackReference
    private List<Consejo> consejos;

    @OneToMany(mappedBy = "paciente")
    private List<Notificacion>notificaciones;

    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL)@JsonBackReference
    private List<Examen> examenes;

    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL)@JsonBackReference
    private List<Tratamiento> tratamientos;

    public Paciente(String pNombre, String pApellido,String pCorreo,Integer pEdad, Double pPeso, Double pEstatura,String pSexo)
    {
        this.nombre = pNombre;
        this.apellido = pApellido;
        this.correo = pCorreo;
        this.edad = pEdad;
        this.peso = pPeso;
        this.estatura = pEstatura;
        this.sexo = pSexo;

        iniciarMarcapaso();
    }

    public Long getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public Double getEstatura() {
        return estatura;
    }

    public Double getPeso() {
        return peso;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setEstatura(Double estatura) {
        this.estatura = estatura;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public static Find<Long,Paciente> find = new Find<Long,Paciente>(){};

    public static Find<String,Paciente> findbyEmail = new Find<String,Paciente>(){};

    public Integer frecuenciaCardiacaMaxima()
    {
        Integer max = 0;
        Integer nPeso = peso.intValue();
        if(sexo.equalsIgnoreCase("mujer"))
        {
            max = ((210 - ((1/2)*edad))-((1/100)*nPeso));
        }
        else
        {
            max = ((210 - ((1/2)*edad))-((1/100)*nPeso))+4;
        }
        return max;
    }

    public void setContraseñaNueva(String contraseña)
    {
        this.contraseña = contraseña;
    }

    public void setContraseña(String contraseñaAntigua, String contsNueva)
    {
        if(this.contraseña.equals(contraseñaAntigua))
        {
            this.contraseña = contsNueva;
        }

    }

    /**
     * Para dos fechas, responde con el arreglo de registros que están dentro de este rango
     * @param fecha1 fecha inicial del rango
     * @param fecha2 fecha final del rango
     * @return
     */
    public List<Registro> darRegistrosEnRangoFechas(Date fecha1, Date fecha2)
    {
        ArrayList<Registro> registrosEnRango = new ArrayList<Registro>();
        for (Registro registro : registros )
            if(registro.getFechaExpedicion().after(fecha1) && registro.getFechaExpedicion().before(fecha2))
                registrosEnRango.add(registro);
        return registrosEnRango;
    }


    public void iniciarMarcapaso(){
        Double frecuenciaInicial = peso/estatura;
        marcapaso = new Marcapaso(frecuenciaInicial);
    }

    public void setRegistro(Registro registro) {
        this.registros.add(registro);
    }

    public void setConsejo(Consejo consejo)
    {
        this.consejos.add(consejo);
    }

    public void setExamen(Examen examen)
    {
        this.examenes.add(examen);
    }

    public void setTratamiento(Tratamiento tratamiento)
    {
        this.tratamientos.add(tratamiento);
    }

    public void setMedicoEspecialista(MedicoEspecialista medicoEspecialista)
    {
        this.medicosEspecialistas.add(medicoEspecialista);
    }

    public void setMedico(Medico medico) {
        this.medicos.add(medico);
    }

    public void setDiagnostico(Diagnostico diagnostico)
    {
        this.diagnosticos.add(diagnostico);
    }

    public void setUrgencia(Urgencia urgencia)
    {
        this.urgencias.add(urgencia);
    }

    public List<Consejo> getConsejos() {
        return consejos;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public List<Urgencia> getUrgencias() {
        return urgencias;
    }

    public List<MedicoEspecialista> getMedicosEspecialistas() {
        return medicosEspecialistas;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    //public void setNotificacion(Notificacion notificacion)
    //{
      //  this.notificaciones.add(notificacion);
    //}

   // public List<Notificacion> getNotificaciones() {
     //   return notificaciones;
    //}

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public List<Examen> getExamenes() {
        return examenes;
    }

    public List<Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public Marcapaso getMarcapaso() {
        return marcapaso;
    }

    public boolean isValid(String email, String password)
    {
        return ((email != null)
                &&
                (password != null)
                &&
                contraseña.equals(password));
    }

}
