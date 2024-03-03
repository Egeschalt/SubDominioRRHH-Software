package com.eguevarapostulante.springcloud.msvc.msvcpostulante.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Postulante")
public class Postulante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String suranme;

    @NotBlank
    @Column(unique = true)
    @Pattern(regexp = "\\d{8}")
    private String dni;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @Pattern(regexp = "\\d{9}")
    private String phone;

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
}
