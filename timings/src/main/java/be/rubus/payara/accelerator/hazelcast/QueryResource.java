package be.rubus.payara.accelerator.hazelcast;

import be.rubus.payara.accelerator.hazelcast.service.CountryService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/query")
@ApplicationScoped
public class QueryResource {

    @Inject
    private CountryService countryService;

    @GET
    @Path("/countries/sql/{continent}")
    public String queryCountriesWithSQL(@PathParam("continent") String continent, @QueryParam("silent") boolean silent) {
        String result = countryService.getCountriesWithSQL(continent);
        if (silent) {
            result = "Countries with SQL";
        }
        return result;
    }

    @GET
    @Path("/countries/predicate/{continent}")
    public String queryCountriesWithPredicate(@PathParam("continent") String continent, @QueryParam("silent") boolean silent) {
        String result = countryService.getCountriesWithPredicates(continent);
        if (silent) {
            result = "Countries with Predicate";
        }
        return result;
    }

    @GET
    @Path("/countries/local/{continent}")
    public String queryCountriesLocal(@PathParam("continent") String continent, @QueryParam("silent") boolean silent) {
        String result = countryService.getCountriesLocal(continent);
        if (silent) {
            result = "Countries through Local";
        }
        return result;
    }

    @GET
    @Path("/countries/slow/{continent}")
    public String queryCountriesSlow(@PathParam("continent") String continent, @QueryParam("silent") boolean silent) {
        String result = countryService.getCountriesLocalSlow(continent);
        if (silent) {
            result = "Countries through Local";
        }
        return result;
    }

}
