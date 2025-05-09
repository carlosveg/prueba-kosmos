package com.dev.projects.pruebakosmos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String paternal;
    private String maternal;
    private String especiality;

    public Doctor() {
    }

    public Doctor(Long id, String name, String paternal, String maternal, String especiality) {
        this.id = id;
        this.name = name;
        this.paternal = paternal;
        this.maternal = maternal;
        this.especiality = especiality;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", paternal='" + paternal + '\'' +
                ", maternal='" + maternal + '\'' +
                ", especiality='" + especiality + '\'' +
                '}';
    }

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

    public String getPaternal() {
        return paternal;
    }

    public void setPaternal(String paternal) {
        this.paternal = paternal;
    }

    public String getMaternal() {
        return maternal;
    }

    public void setMaternal(String maternal) {
        this.maternal = maternal;
    }

    public String getEspeciality() {
        return especiality;
    }

    public void setEspeciality(String especiality) {
        this.especiality = especiality;
    }
}
