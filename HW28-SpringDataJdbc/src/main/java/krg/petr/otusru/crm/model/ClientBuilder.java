package krg.petr.otusru.crm.model;

import java.util.Set;

public class ClientBuilder {
    private Long id;
    private String name;
    private Address address;
    private Set<Phone> phones;

    public ClientBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder setAddress(Address address) {
        this.address = address;
        return this;
    }

    public ClientBuilder setPhones(Set<Phone> phones) {
        this.phones = phones;
        return this;
    }

    public Client build() {
        return new Client(id, name, address, phones);
    }
}
