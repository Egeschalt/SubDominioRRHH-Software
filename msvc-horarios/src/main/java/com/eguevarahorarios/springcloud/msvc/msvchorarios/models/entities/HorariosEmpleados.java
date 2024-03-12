package com.eguevarahorarios.springcloud.msvc.msvchorarios.models.entities;

import jakarta.persistence.*;
@Entity
@Table(name="Horarios_Empleados")
public class HorariosEmpleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="empleados_id", unique = true)
    private Long empleadosId;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getEmpleadosId() {
        return empleadosId;
    }
    public void setEmpleadosId(Long empleadosId) {
        this.empleadosId = empleadosId;
    }
    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(!(obj instanceof HorariosEmpleados)){
            return false;
        }
        HorariosEmpleados o = (HorariosEmpleados) obj;
        return this.empleadosId != null && this.empleadosId.equals(o.empleadosId);
    }
}
