package com.eguevaraplanilla.springcloud.msvc.msvcplanilla.models;

public class Empleadoss {
    private Long id;
    private String name;
    private String suranme;
    private String dni;
    private String email;
    private String phone;
    private Double sueldo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuranme() {
        return suranme;
    }

    public void setSuranme(String suranme) {
        this.suranme = suranme;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }
}
