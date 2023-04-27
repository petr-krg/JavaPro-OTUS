package krg.petr.otusru.homework;

import java.util.Stack;

public class CustomerReverseOrder {

        //todo: 2. надо реализовать методы этого класса
        //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

        // Для реализации данных методов выбрал стэк, так как он позволяет извлекать данные в порядке "первый пришел - последним ушел"
        // И код получиться в несколько строк, что соотвествует данному заданию.

        private final Stack<Customer> stackCustomerOrder = new Stack<>();;

        public void add(Customer customer) {

            this.stackCustomerOrder.push(customer);
        }

        public Customer take() {

            return this.stackCustomerOrder.pop();
        }
}
