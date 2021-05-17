package be.rubus.payara.accelerator.hazelcast;

import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.sql.SqlResult;
import com.hazelcast.sql.SqlRow;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Collection;

@Path("/query")
@ApplicationScoped
public class QueryResource {

    @Inject
    private HazelcastInstance hzInstance;

    @GET
    public String getObjects() {
        StringBuilder result = new StringBuilder();
        result.append("Distributed Objects");
        Collection<DistributedObject> distributedObjects = hzInstance.getDistributedObjects();
        for (DistributedObject object : distributedObjects) {
            result.append("\n").append(object.getName());
        }

        return result.toString();
    }

    @GET
    @Path("/sessions")
    public String querySessions() {
        StringBuilder result = new StringBuilder();
        result.append("sessions");

        try (SqlResult queryResult = hzInstance.getSql().execute("SELECT name FROM sessions")) {
            for (SqlRow row : queryResult) {
                String name = row.getObject(0);

                result.append("\n").append(name);
            }
        }
        return result.toString();
    }
}
