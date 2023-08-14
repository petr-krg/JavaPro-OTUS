package krg.petr.otusru.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import krg.petr.otusru.crm.service.DBServiceClient;
import krg.petr.otusru.services.AuthService;
import krg.petr.otusru.services.TemplateProcessor;
import krg.petr.otusru.servlet.AuthorizationFilter;
import krg.petr.otusru.servlet.LoginServlet;
import java.util.Arrays;

public class WebServerSecurityImpl extends WebServerImpl{
    private final AuthService authService;
    public WebServerSecurityImpl(int port, DBServiceClient dbServiceClient, TemplateProcessor templateProcessor, AuthService authService) {
        super(port, dbServiceClient, templateProcessor);
        this.authService = authService;
    }

    @Override
    protected Handler applySecurity(ServletContextHandler servletContextHandler, String... paths) {
        servletContextHandler.addServlet(new ServletHolder(new LoginServlet(templateProcessor, authService)), "/login");
        AuthorizationFilter authorizationFilter = new AuthorizationFilter();
        Arrays.stream(paths).forEachOrdered(path -> servletContextHandler.addFilter(new FilterHolder(authorizationFilter), path, null));
        return servletContextHandler;
    }
}
