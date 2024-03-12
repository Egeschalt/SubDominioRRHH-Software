package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.entities;


import com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models.Empleadoss;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Planillas")
public class Planilla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Pattern(regexp = "\\d{10}")
    private String numerorecibo;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @NonNull
    private Date fechaliquidacion;
    @NotNull
    @DecimalMin(value = "0.0")
    private Double liquidacion;
    @NotNull
    @DecimalMin(value = "0.0")
    private Double descuentos;//horas no trabajadas, descuentos, bajo rendimiento
    @NotNull
    @DecimalMin(value = "0.0")
    private Double Ingresoimponible;//horas extra,gratifiaciones,rendimiento
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "planilla_id")
    private List<PlanillaEmpleados> planillaEmpleadosList;
    @Transient
    private List<Empleadoss> empleados;

    public Planilla() {
        planillaEmpleadosList = new ArrayList<>();
        empleados = new ArrayList<>();
    }

    public List<Empleadoss> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleadoss> empleados) {
        this.empleados = empleados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getNumerorecibo() {
        return numerorecibo;
    }

    public void setNumerorecibo(@NonNull String numerorecibo) {
        this.numerorecibo = numerorecibo;
    }

    @NonNull
    public Date getFechaliquidacion() {
        return fechaliquidacion;
    }

    public void setFechaliquidacion(@NonNull Date fechaliquidacion) {
        this.fechaliquidacion = fechaliquidacion;
    }

    @NonNull
    public Double getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(@NonNull Double liquidacion) {
        this.liquidacion = liquidacion;
    }

    public Double getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(Double descuentos) {
        this.descuentos = descuentos;
    }

    public Double getIngresoimponible() {
        return Ingresoimponible;
    }

    public void setIngresoimponible(Double ingresoimponible) {
        Ingresoimponible = ingresoimponible;
    }


    public void addPlanillaEmpleados(PlanillaEmpleados planillaEmpleados){
        planillaEmpleadosList.add(planillaEmpleados);
    }
    public void removeCursoUsuario(PlanillaEmpleados planillaEmpleados){
        planillaEmpleadosList.remove(planillaEmpleados);
    }

    public List<PlanillaEmpleados> getPlanillaEmpleadosList() {
        return planillaEmpleadosList;
    }

    public void setPlanillaEmpleadosList(List<PlanillaEmpleados> planillaEmpleadosList) {
        this.planillaEmpleadosList = planillaEmpleadosList;
    }
}
