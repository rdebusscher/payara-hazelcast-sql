package be.rubus.payara.accelerator.hazelcast.service;

import be.rubus.payara.accelerator.hazelcast.model.Country;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.sql.SqlResult;
import com.hazelcast.sql.SqlRow;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CountryService {

    @Inject
    private HazelcastInstance hzInstance;

    @Inject
    private CountryBoundary countryBoundary;

    @PostConstruct
    public void init() {
        List<Country> countries = countryBoundary.getCountries();
        IMap<Object, Object> countryMap = hzInstance.getMap("countries");
        countries.forEach(c -> countryMap.put(c.getId(), c));
    }

    public String getCountries(String continent) {
        StringBuilder result = new StringBuilder();
        result.append("countries");

        try (SqlResult queryResult = hzInstance.getSql().execute("SELECT name FROM countries WHERE continentName = ?", continent)) {
            for (SqlRow row : queryResult) {
                String name = row.getObject(0);

                result.append("\n").append(name);
            }
        }
        return result.toString();

    }
}
