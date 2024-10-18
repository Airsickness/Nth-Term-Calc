import java.util.ArrayList;
import java.util.Objects;

public class NthTermCalc {
    public static void findLinearNthTerm(ArrayList<Integer> list, String mode) {
        ArrayList<Integer> temp1  = new ArrayList<>();
        boolean linear = false;
        int quadDiff = 0;
        int zerothTerm = 0;
        for (int counter = 0; counter < list.size() - 1; counter++) {
            Integer value = list.get(counter + 1) - list.get(counter);
            temp1.add(value);
        }
        if (Objects.equals(temp1.get(1), temp1.get(0))) {
            //working out the linear nth term
            linear = true;
            int difference = list.get(1) - list.get(0);
            zerothTerm = list.get(0) - difference;
            if (mode.equals("p")) {
                System.out.print(temp1.getFirst() + "n " + zerothTerm);
            } else if (mode.equals("f")) {
                System.out.println("The nth term is: " + temp1.getFirst() + "n " + zerothTerm);
            }
        } else {
            System.out.println("The sequence is not linear");
        }
    }



    public static void findNthTerm(ArrayList<Integer> list) {
        ArrayList<Integer> temp1 = new ArrayList<>(list.size());
        ArrayList<Integer> temp2 = new ArrayList<>();
        ArrayList<Integer> last = new ArrayList<>();
        ArrayList<Integer> baseNSquared = new ArrayList<>();
        ArrayList<Integer> finalToLinear = new ArrayList<>();
        // populating base n^2 list
        for (int counter = 1; counter < list.size() + 1; counter++) {
            baseNSquared.add(counter * counter);
        }
        // populating finalToLinear list
        for (int counter =0; counter < 3; counter++) {
            finalToLinear.add(counter);
        }
        boolean quadratic = false;
        boolean linear = false;
        int quadDiff = 0;
        int zerothTerm = 0;
        int coefficient = 1;
        boolean flag = false;
        // finding the first difference
        breaker:
        while (!flag) {
            for (int counter = 0; counter < list.size() - 1; counter++) {
                Integer value = list.get(counter + 1) - list.get(counter);
                temp1.add(value);
            }
            if (Objects.equals(temp1.get(1), temp1.get(0)) && Objects.equals(temp1.get(1), temp1.get(2))) {
                //working out the linear nth term
                linear = true;
                int difference = list.get(1) - list.get(0);
                zerothTerm = list.get(0) - difference;
                System.out.println("The nth term formula is: " + temp1.getFirst() + "n " + zerothTerm);
                flag = true;
                break;
            }
            //working out the second difference for a quadratic
            for (int counter = 0; counter < temp1.size() - 1; counter++) {
                quadDiff = temp1.get(counter + 1) - temp1.get(counter);
                temp2.add(quadDiff);
            }
            if (Objects.equals(temp2.get(1), temp2.get(0)) && !linear) {
                // the sequence is quadratic
                quadratic = true;
                coefficient = quadDiff / 2;
            } else {
                System.out.println("The sequence is not quadratic.");
                flag = true;
                break;

            }
            // modifying baseNSquared by multiplying each element by the coefficient
            for (int counter = 0; counter < baseNSquared.size(); counter++) {
                baseNSquared.set(counter, baseNSquared.get(counter) * coefficient);

            }
            // preparing to call the linear method
            for (int counter = 0; counter < temp2.size(); counter++) {
                finalToLinear.set(counter, temp2.get(counter) - baseNSquared.get(counter));
            }
            for (int counter = 0; counter < list.size(); counter++) {
                last.add(list.get(counter) - baseNSquared.get(counter));
            }
            if (Objects.equals(last.get(0), last.get(1)) && Objects.equals(last.get(1), last.get(2))) {
                System.out.println("The nth term is: " + coefficient + "n^2 + " + last.getFirst());

            } else {
                System.out.print("The nth term is: " + coefficient + "n^2 ");
                findLinearNthTerm(last, "p");
            }


            flag = true;

        }
    }


    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(58);
        test.add(161);
        test.add(328);
        test.add(559);
        test.add(854);



        findNthTerm(test);

    }
}
