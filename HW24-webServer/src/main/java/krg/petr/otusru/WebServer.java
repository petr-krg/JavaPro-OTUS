package krg.petr.otusru;

import krg.petr.otusru.core.repository.DataTemplateHibernate;
import krg.petr.otusru.core.repository.HibernateUtils;
import krg.petr.otusru.core.sessionmanager.TransactionManagerHibernate;
import krg.petr.otusru.crm.dbmigrations.MigrationsExecutorFlyway;
import krg.petr.otusru.crm.model.Address;
import krg.petr.otusru.crm.model.Client;
import krg.petr.otusru.crm.model.Phone;
import krg.petr.otusru.crm.service.DbServiceClientImpl;
import krg.petr.otusru.server.WebServerSecurityImpl;
import krg.petr.otusru.services.AuthServiceImpl;
import krg.petr.otusru.services.TemplateProcessorImpl;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class WebServer {
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        var dbUrl = configuration.getProperty("hibernate.connection.url");
        var dbUserName = configuration.getProperty("hibernate.connection.username");
        var dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();
        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, Address.class, Phone.class);
        var transactionManager = new TransactionManagerHibernate(sessionFactory);
        var clientTemplate = new DataTemplateHibernate<>(Client.class);
        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate);

        // добавляем пользователя для авторизации и последующей работы
        dbServiceClient.saveClient(new Client(null, "petr.krg", new Address(null, "Kazakhstan, Karaganda"),
                                    List.of(new Phone(null, "+7(702)999-99-99"),
                                            new Phone(null, "+7(777)999-99-99")),
                                    "root", "11111111"));

        var templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        var authService = new AuthServiceImpl(dbServiceClient);

        var webServer = new WebServerSecurityImpl(WEB_SERVER_PORT, dbServiceClient, templateProcessor, authService);

        webServer.start();
        webServer.join();
    }
}