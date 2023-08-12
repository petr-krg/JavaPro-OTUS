package krg.petr.otusru.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import krg.petr.otusru.crm.model.Address;
import krg.petr.otusru.crm.model.Client;
import krg.petr.otusru.crm.model.Phone;
import krg.petr.otusru.crm.service.DBServiceClient;
import krg.petr.otusru.services.TemplateProcessor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servlet extends HttpServlet {
    private static final String PAGE_TEMPLATE_CLIENTS = "clients.html";
    private static final String TEMPLATE_ATTR_CLIENTS = "clients";
    private final TemplateProcessor templateProcessor;
    private final DBServiceClient dbServiceClient;

    public Servlet(TemplateProcessor templateProcessor, DBServiceClient dbServiceClient) {
        this.dbServiceClient = dbServiceClient;
        this.templateProcessor = templateProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(TEMPLATE_ATTR_CLIENTS, dbServiceClient.findAll());

        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(PAGE_TEMPLATE_CLIENTS, paramsMap));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name     = request.getParameter("name");
        String address  = request.getParameter("address");
        String phone    = request.getParameter("phone");
        String login    = request.getParameter("login");
        String password = request.getParameter("password");

        dbServiceClient.saveClient(new Client(null, name, new Address(null, address), List.of(new Phone(null, phone)), login, password));
        response.sendRedirect("/clients");
    }
}
