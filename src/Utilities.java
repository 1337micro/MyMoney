//-------------------------------------------------------
//For Comp 354 Section PP - Winter 2018
//Iteration 1: William Prioriello
//Description: Class with helper methods
// --------------------------------------------------------
package src;

import java.util.List;

public class Utilities {
    /**
     * Sum a list of doubles
     * @param list a list of doubles
     * @return
     */
    static Double sumListOfNumbers(List<Double> list){
        return list.stream().mapToDouble((Double x) -> x).sum();
    }
}
