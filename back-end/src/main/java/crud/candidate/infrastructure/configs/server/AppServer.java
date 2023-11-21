package crud.candidate.infrastructure.configs.server;

import crud.candidate.application.controllers.CandidateController;
import crud.candidate.infrastructure.configs.db.DBConnection;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class AppServer {

    private static final Integer SERVER_PORT = 9009;

    private static final String CANDIDATE_PATH = "/candidate/*";

    public static void initServer() {
        try {
            DBConnection.getConnection();
            Server server = new Server(SERVER_PORT);

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            server.setHandler(context);

            context.addServlet(new ServletHolder(new CandidateController()), CANDIDATE_PATH);

            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
