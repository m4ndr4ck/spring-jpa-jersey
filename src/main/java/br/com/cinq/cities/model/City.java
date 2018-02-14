package br.com.cinq.cities.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dvsjunior on 14/02/2018.
 */
@Entity
@Table(name = "City")
public class City {

    private long id;
    private String name;
    private Country country;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
