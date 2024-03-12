package com.eguevarahorarios.springcloud.msvc.msvchorarios.models.entities;

import com.eguevarahorarios.springcloud.msvc.msvchorarios.models.Empleados;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

import java.sql.Date;

@Entity
@Table(name = "Horarios")
public class Horarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @DecimalMin(value = "0.0")
    private Double horas;
    @NotBlank
    @Pattern(regexp = "^(mañana|tarde)$", message = "El turno debe ser 'mañana' o 'tarde'")
    private String turno;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NonNull
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="horarios_id")
    private List<HorariosEmpleados> listahorariosEmpleados;
    @Transient
    private List<Empleados> empleados;
    public Horarios() {
        listahorariosEmpleados = new ArrayList<>();
        empleados = new ArrayList<>();
    }

    public void addHorariosEmpleados(HorariosEmpleados horariosEmpleados){
        listahorariosEmpleados.add(horariosEmpleados);
    }
    public void removeHorariosEmpleados(HorariosEmpleados horariosEmpleados){
        listahorariosEmpleados.remove(horariosEmpleados);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public Double getHoras() {
        return horas;
    }

    public void setHoras(@NonNull Double horas) {
        this.horas = horas;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @NonNull
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(@NonNull Date fecha) {
        this.fecha = fecha;
    }

    public List<HorariosEmpleados> getListahorariosEmpleados() {
        return listahorariosEmpleados;
    }

    public void setListahorariosEmpleados(List<HorariosEmpleados> listahorariosEmpleados) {
        this.listahorariosEmpleados = listahorariosEmpleados;
    }

    public List<Empleados> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleados> empleados) {
        this.empleados = empleados;
    }
}
