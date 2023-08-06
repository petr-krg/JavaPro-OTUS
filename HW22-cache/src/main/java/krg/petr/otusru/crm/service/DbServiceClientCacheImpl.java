package krg.petr.otusru.crm.service;

import krg.petr.otusru.cache.Cache;
import krg.petr.otusru.crm.model.Client;

import java.util.List;
import java.util.Optional;

public class DbServiceClientCacheImpl implements DBServiceClient{
    private final DBServiceClient dbServiceClient;

    private final Cache<String, Client> dbServiceClientCache;

    public DbServiceClientCacheImpl(DBServiceClient dbServiceClient, Cache<String, Client> dbServiceClientCache) {
        this.dbServiceClient = dbServiceClient;
        this.dbServiceClientCache = dbServiceClientCache;
    }

    @Override
    public Client saveClient(Client client) {
        Client savedClient = dbServiceClient.saveClient(client.clone());
        dbServiceClientCache.put(String.valueOf(savedClient.getId()), savedClient);
        return savedClient;
    }

    @Override
    public Optional<Client> getClient(long id) {
        Client client = dbServiceClientCache.get(String.valueOf(id));
        if (client != null) {
            return Optional.of(client.clone());
        }

        return dbServiceClient.getClient(id).map(value -> {
            Client cloneValue = value.clone();
            dbServiceClientCache.put(String.valueOf(value.getId()), cloneValue);
            return cloneValue;
        });
    }

    @Override
    public List<Client> findAll() {
        return dbServiceClient.findAll();
    }
}
