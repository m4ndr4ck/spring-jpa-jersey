package br.com.cinq.cities.model;

import javax.persistence.*;

/**
 * Created by dvsjunior on 14/02/2018.
 */
@Entity
@Table(name = "Country")
public class Country {

    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
