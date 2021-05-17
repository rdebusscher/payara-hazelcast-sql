package be.rubus.payara.accelerator.hazelcast.service;

import be.rubus.payara.accelerator.hazelcast.model.Country;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CountryBoundary {

    @PersistenceContext
    private EntityManager em;

    public List<Country> getCountries() {
        return em.createQuery("SELECT c FROM Country c", Country.class).getResultList();
    }
}
