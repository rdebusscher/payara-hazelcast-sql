package be.rubus.payara.accelerator.hazelcast.service;

import be.rubus.payara.accelerator.hazelcast.model.Country;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.hazelcast.query.Predicates;
import com.hazelcast.sql.SqlResult;
import com.hazelcast.sql.SqlRow;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class CountryService {

    @Inject
    private HazelcastInstance hzInstance;

    public String getCountriesWithSQL(String continent) {
        StringBuilder result = new StringBuilder();
        result.append("countries (using Hazelcast SQL)");

        try (SqlResult queryResult = hzInstance.getSql().execute("SELECT name FROM countries WHERE continentName = ?", continent)) {
            for (SqlRow row : queryResult) {
                String name = row.getObject(0);

                result.append("\n").append(name);
            }
        }
        return result.toString();

    }

    public String getCountriesWithPredicates(String continent) {
        StringBuilder result = new StringBuilder();
        result.append("countries (using Hazelcast Predicate)");

        IMap<Long, Country> countryMap = hzInstance.getMap("countries");

        PredicateBuilder.EntryObject e = Predicates.newPredicateBuilder().getEntryObject();
        Predicate predicate = e.get("continentName").equal(continent);

        Collection<Country> countries = countryMap.values(predicate);
        countries.stream().map(c -> "\n" + c.getName()).collect(Collectors.toList());

        return result.toString();

    }

    public String getCountriesLocal(String continent) {
        StringBuilder result = new StringBuilder();
        result.append("countries (using local filtering)");

        IMap<Long, Country> countryMap = hzInstance.getMap("countries");

        result.append(
                countryMap.values().stream()
                        .filter(c -> continent.equals(c.getContinentName()))
                        .map(Country::getName)
                        .collect(Collectors.joining("\n"))
        );
        return result.toString();
    }

    public String getCountriesLocalSlow(String continent) {
        StringBuilder result = new StringBuilder();
        result.append("countries (using local filtering)");

        IMap<Long, Country> countryMap = hzInstance.getMap("countries");

        for (Map.Entry<Long, Country> entry : countryMap) {
            if (continent.equals(entry.getValue().getContinentName())) {
                result.append("\n").append(entry.getValue().getName());
            }
        }


        return result.toString();
    }


}
