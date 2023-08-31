package krg.petr.otusru.crm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("address")
public class Address {

    @Id
    private final Long id;

    @Column("address")
    private final String address;

    public Address(String address) {
        this(null, address);
    }

    @PersistenceCreator
    public Address(Long id, String address) {
        this.id = id;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + "'" +
                "}";
    }
}
