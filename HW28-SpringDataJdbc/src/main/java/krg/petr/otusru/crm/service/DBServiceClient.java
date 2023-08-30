package krg.petr.otusru.crm.service;

import krg.petr.otusru.crm.model.Client;

public interface DBServiceClient {

    Client saveClient(Client client);

    Iterable<Client> findAll();
}
