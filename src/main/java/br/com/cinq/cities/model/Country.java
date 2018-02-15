package br.com.cinq.cities.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dvsjunior on 14/02/2018.
 */
@Entity
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<City> city;

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
