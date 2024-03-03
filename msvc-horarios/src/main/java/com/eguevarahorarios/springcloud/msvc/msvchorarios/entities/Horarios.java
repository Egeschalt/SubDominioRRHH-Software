package com.eguevarahorarios.springcloud.msvc.msvchorarios.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;


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
}
