package nicebank;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WithdrawalServlet extends HttpServlet {

    private CashSlot cashSlot;
    private Account account;

    public WithdrawalServlet(CashSlot cashSlot, Account account) {
        this.cashSlot = cashSlot;
        this.account = account;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Teller teller = new AutomatedTeller(cashSlot);
        int amount = Integer.parseInt(request.getParameter("amount"));
        System.out.println("###########################[WithdrawalServlet] > doPost() > withdraw money: " +
                amount + ", from cashslot: " + cashSlot + ", for Account: " + account);
        teller.withdrawFrom(account, amount);

        response.setContentType("text/html");
        response.setStatus(javax.servlet.http.HttpServletResponse.SC_OK);
        response.getWriter().println(
                "<html><head><title>Nice Bank ATM</title></head>" +
                        "<body>Please take your $" + amount + "</body></html>");
        System.out.println("#####################################[WithdrawalServlet] > doPost(" + account + ", " + amount + ") -> Take money: " + amount);
    }
}