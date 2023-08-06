package krg.petr.otusru;

import krg.petr.otusru.cache.CacheImpl;
import krg.petr.otusru.core.repository.DataTemplateHibernate;
import krg.petr.otusru.core.repository.HibernateUtils;
import krg.petr.otusru.core.sessionmanager.TransactionManagerHibernate;
import krg.petr.otusru.crm.dbmigrations.MigrationsExecutorFlyway;
import krg.petr.otusru.crm.model.Address;
import krg.petr.otusru.crm.model.Client;
import krg.petr.otusru.crm.model.Phone;
import krg.petr.otusru.crm.service.DbServiceClientCacheImpl;
import krg.petr.otusru.crm.service.DbServiceClientImpl;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DbServiceCache {
    private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) {
        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        var dbUrl = configuration.getProperty("hibernate.connection.url");
        var dbUserName = configuration.getProperty("hibernate.connection.username");
        var dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, Address.class, Phone.class);

        var transactionManager = new TransactionManagerHibernate(sessionFactory);
///
        var clientTemplate = new DataTemplateHibernate<>(Client.class);
///
        var dbServiceClientCache = new DbServiceClientCacheImpl(new DbServiceClientImpl(transactionManager, clientTemplate),
                                                                new CacheImpl<>());


        dbServiceClientCache.saveClient(new Client(null, "Client1",
                                        new Address(null, "Grove Street"),
                                        List.of(new Phone(null, "8-800-555-22"),
                                        new Phone(null, "8-800-555-40"))));
        dbServiceClientCache.saveClient(new Client(null, "Client2",
                                        new Address(null, "Grove Street"),
                                        List.of(new Phone(null, "8-800-555-22"),
                                        new Phone(null, "8-800-555-40"))));
        dbServiceClientCache.saveClient(new Client(null, "Client3",
                                        new Address(null, "Grove Street"),
                                        List.of(new Phone(null, "8-800-555-22"),
                                        new Phone(null, "8-800-555-40"))));
        dbServiceClientCache.saveClient(new Client(null, "Client4",
                                        new Address(null, "Grove Street"),
                                        List.of(new Phone(null, "8-800-555-22"),
                                        new Phone(null, "8-800-555-40"))));

        dbServiceClientCache.getClient(2L);
        dbServiceClientCache.getClient(3L);
        dbServiceClientCache.findAll();

    }
}
