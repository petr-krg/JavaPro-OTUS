package krg.petr.otusru.crm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @SequenceGenerator(name = "address_gen", sequenceName = "address_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    public Address(Long id, String address) {
        this.id = id;
        this.street = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + street + "'" +
                "}";
    }
}
