package krg.petr.otusru.controller;

import jakarta.servlet.http.HttpServletRequest;
import krg.petr.otusru.crm.model.Address;
import krg.petr.otusru.crm.model.Client;
import krg.petr.otusru.crm.model.ClientBuilder;
import krg.petr.otusru.crm.model.Phone;
import krg.petr.otusru.crm.service.DBServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashSet;
import java.util.Set;

@Controller
public class ClientController {

    private final DBServiceClient serviceClient;

    public ClientController(DBServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "index.html";
    }

    @GetMapping("/clients")
    public String viewClients(Model model) {
        Iterable<Client> clients = serviceClient.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("client", new ClientBuilder().setName("")
                                                                     .setAddress(new Address(""))
                                                                     .setPhones(new HashSet<>())
                                                                     .build());
        return "clients";
    }

    @PostMapping("/clients")
    public RedirectView saveClients(String name, String street, HttpServletRequest request) {
        String[] phones = request.getParameterValues("phones[].number");
        Set<Phone> phoneNumbers = new HashSet<>();
        for(String phone : phones) {
            phoneNumbers.add(new Phone(phone));
        }
        serviceClient.saveClient(new Client(name, new Address(street), phoneNumbers));
        return new RedirectView("/clients", true);
    }

}
