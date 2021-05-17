package be.rubus.payara.accelerator.hazelcast;

import be.rubus.payara.accelerator.hazelcast.service.CountryService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/query")
@ApplicationScoped
public class QueryResource {

    @Inject
    private CountryService countryService;

    @GET
    @Path("/countries/{continent}")
    public String queryCountries(@PathParam("continent") String continent) {
        return countryService.getCountries(continent);
    }
}
