package krg.petr.otusru.homework;

import java.util.*;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private TreeMap<Customer, String> customers = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Customer result = customers.firstEntry().getKey();
        return new AbstractMap.SimpleEntry<>(new Customer(result.getId(),
                                                          result.getName(),
                                                          result.getScores()),
                                                          customers.get(result));
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Customer result = customers.higherKey(customer);
        return  result == null ? null : new AbstractMap.SimpleEntry<>(new Customer(result.getId(),
                                                                                   result.getName(),
                                                                                   result.getScores()),
                                                                                   customers.get(result));
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }

}
