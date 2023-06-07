package com.OvaEducativaAPI.modelo;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinColumn;


@Entity
@Table(name="usuario", uniqueConstraints = {@UniqueConstraint(name = "unique_email", columnNames= {"email"})})
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;

    @ElementCollection
    @CollectionTable(name = "user_notas", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "nota")
    private Double[] notas;
    
    // Full constructor

    public User() {
    }

      public void setId(Integer id) {
        this.id = id;
    }


    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double[] getNotas() {
        return notas;
    }

    public void setNotas(Double[] notas) {
        this.notas = notas;
    }

    


}