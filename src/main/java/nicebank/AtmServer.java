package nicebank;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.javalite.activejdbc.Base;

public class AtmServer
{
    private final Server server;

    public AtmServer(int port, CashSlot cashSlot, Account account) {
        server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        //context.addServlet(new ServletHolder(new GetHomePageServlet()),"/homepage");
        context.addServlet(new ServletHolder(new WithdrawalServlet(cashSlot, account)),"/withdraw");
        context.addServlet(new ServletHolder(new AtmServlet()),"/");
        System.out.println("========================= [AtmServer] > [constructor] > Servlet's added");
    }

    public void start() throws Exception {
        server.start();
        System.out.println("========================= [AtmServer] > [start] > Listening on " + server.getURI());
    }

    public void stop() throws Exception {
        server.stop();
        System.out.println("========================= [AtmServer] > [stop] > Listening on " + server.getURI());
    }

    public static void main(String[] args) throws Exception {
        System.out.println("££££££££££££££ [AtmServer] > main() > Base.open(mysql stuff).  Then - new AtmServer(port=9988)");
        Base.open(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost/bank",
                "teller", "password");
        new AtmServer(9988, new CashSlot(), new Account()).start();
    }
}