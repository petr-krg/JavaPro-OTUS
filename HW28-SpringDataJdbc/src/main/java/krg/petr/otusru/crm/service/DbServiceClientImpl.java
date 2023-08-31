package krg.petr.otusru.crm.service;

import krg.petr.otusru.crm.repository.ClientRepository;
import krg.petr.otusru.crm.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DbServiceClientImpl implements DBServiceClient {
    private static final Logger log = LoggerFactory.getLogger(DbServiceClientImpl.class);

    private final ClientRepository clientRepository;

    public DbServiceClientImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Iterable<Client> findAll() {
            return clientRepository.findAll();
       }
}
