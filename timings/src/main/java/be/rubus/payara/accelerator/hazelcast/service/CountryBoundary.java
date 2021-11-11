package be.rubus.payara.accelerator.hazelcast.service;

import be.rubus.payara.accelerator.hazelcast.model.Data;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.InputStream;

@ApplicationScoped
public class CountryBoundary {

    @Inject
    private HazelcastInstance hzInstance;

    @PostConstruct
    public void loadData(@Observes @Initialized(ApplicationScoped.class) Object init) {
        Jsonb jsonb = JsonbBuilder.create();
        InputStream in = this.getClass()
                .getResourceAsStream("/data.json");
        Data data = jsonb.fromJson(in, Data.class);
        IMap<Object, Object> countryMap = hzInstance.getMap("countries");
        data.getCountries().forEach(c -> countryMap.put(c.getId(), c));

    }

}
