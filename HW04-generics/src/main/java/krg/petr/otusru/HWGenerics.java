package krg.petr.otusru;

import krg.petr.otusru.homework.Customer;

public class HWGenerics {
    public static void main(String[] args) {
        Customer customer = new Customer(1, "petr.krg", 2);
        System.out.println(customer.getName());
        System.out.println(customer.equals(new Customer(1, "petr.krg", 2)));
        System.out.println(customer.hashCode());
        System.out.println(customer.toString());

    }
}
