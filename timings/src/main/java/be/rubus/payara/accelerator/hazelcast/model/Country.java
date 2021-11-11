package be.rubus.payara.accelerator.hazelcast.model;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Country")
public class Country implements Serializable {

    @Id
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;

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

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    @JsonbTransient
    public String getContinentName() {
        return continent.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }

        Country country = (Country) o;

        return name.equals(country.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", continent=" + continent +
                '}';
    }
}
