package krg.petr.otusru;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class HelloOtus {
    public static void main(String[] args) {

        var integerList = Lists.newArrayList(5, 6, 10, null, 8, 11, null, 33);

        Iterable<Integer> filterInteger = Iterables.filter(integerList, Predicates.notNull());

        for (Integer i: filterInteger) {
            System.out.println(i);
        }
    }

}