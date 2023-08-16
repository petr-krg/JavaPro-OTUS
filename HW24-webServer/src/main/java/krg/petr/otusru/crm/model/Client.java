package krg.petr.otusru.crm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client implements Cloneable {

    @Id
    @SequenceGenerator(name = "client_gen", sequenceName = "client_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "login")
    private String login;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;


    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @Fetch(value = FetchMode.JOIN)
    private Address street;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Phone> phone = new ArrayList<>();

    public Client(String name) {
        this.name = name;
    }

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(Long id, String name, Address address, List<Phone> phones) {
        this.id = id;
        this.name = name;

        setStreet(address);
        setPhone(phones);
    }

    public Client(Long id, String name, Address address, List<Phone> phones, String login, String password) {
        this.id = id;
        this.name = name;

//        setLogin(login != null && !login.isEmpty() ? login : null);
//        setPassword(password != null && !password.isEmpty() ? password : null);
        setLogin(login);
        setPassword(password);
        setStreet(address);
        setPhone(phones);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getStreet() {
        return street;
    }

    public void setStreet(Address street) {
        if (street != null) {
            this.street = new Address(street.getId(), street.getStreet(), this);
        } else {
            this.street = null;
        }
    }

    public List<Phone> getNumbers() {
        return phone;
    }

   public void setPhone(List<Phone> phones) {
        this.phone = phones.stream()
                .map(p -> new Phone(p.getId(), p.getPhone(), this))
                .collect(Collectors.toList());
   }

   public String getAllNumbers() {
       return String.join(", ", getNumbers().stream().map(Phone::getPhone).toList());
    }

    @Override
    public Client clone() {
        return new Client(this.id, this.name, this.street, this.phone, this.login, this.password);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
