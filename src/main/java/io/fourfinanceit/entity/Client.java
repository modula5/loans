package io.fourfinanceit.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "CLIENTS")
public class Client {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String code;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String lastName;

    public Client() {
    }

    public Client(String code, String name, String lastName) {
        this.code = code;
        this.name = name;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(code, client.code) &&
                Objects.equals(name, client.name) &&
                Objects.equals(lastName, client.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code, name, lastName);
    }
}
