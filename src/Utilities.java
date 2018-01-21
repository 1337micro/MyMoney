package src;

import java.util.List;

public class Utilities {
    static Double sumListOfNumbers(List<Double> list){
        return list.stream().mapToDouble((Double x) -> x).sum();
    }
}
