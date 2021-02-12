package nicebank;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AtmServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(
                "<html><head><title>ATM Withdrawal</title></head>" +
                        "<body><form action=\"/withdraw\" method=\"post\">" +
                        "<label for=\"amount\">Amount</label>" +
                        "<input type=\"text\" id=\"amount\" name=\"amount\">" +
                        "<button type=\"submit\" id=\"withdraw\">Withdraw</button>" +
                        "</form></body></html>");
    }
}

