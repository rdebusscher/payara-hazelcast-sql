package be.rubus.payara.accelerator.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/hello")
public class SessionServlet extends HttpServlet {

    @Inject
    private HazelcastInstance hzInstance;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = new Person();
        String userName = req.getParameter("user");
        person.setName(userName);

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", person);
        resp.getWriter().println(String.format("Created HttpSession for %s", userName));

        IMap<Object, Object> sessions = hzInstance.getMap("sessions");
        sessions.put(httpSession.getId(), person);

    }
}
