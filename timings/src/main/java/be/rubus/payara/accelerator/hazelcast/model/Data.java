package be.rubus.payara.accelerator.hazelcast.model;

import java.util.List;

public class Data {

    private List<Continent> continents;
    private List<Country> countries;

    public Data() {
    }

    public Data(List<Continent> continents, List<Country> countries) {
        this.continents = continents;
        this.countries = countries;
    }

    public List<Continent> getContinents() {
        return continents;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setContinents(List<Continent> continents) {
        this.continents = continents;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
