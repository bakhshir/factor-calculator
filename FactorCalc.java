import java.util.*;

// calculation of factors, collaboration with Hans A.

public class FactorCalc {


    public static void main(String args[]) {

        int nrOfLevels = 1;
        ArrayList<ArrayList<Integer>> factors = new ArrayList<ArrayList<Integer>>();

        if (args.length > 0) {
            nrOfLevels = Integer.parseInt(args[0]);
        }

        ArrayList<Integer> zero = new ArrayList<Integer>();
        zero.add(0);
        factors.add(zero);
        
        for (int level=0; level < nrOfLevels; level++) {
            //Here we go over and produce the thing
            ArrayList<ArrayList<Integer>> new_factors = new ArrayList<ArrayList<Integer>>();
            
            for(ArrayList<Integer> factor : factors) {
                for(int index = 0; index < factor.size(); index++) {
                    ArrayList<Integer> new_factor = new ArrayList<Integer>(factor);
                    int element = new_factor.remove(index);
                    new_factor.add(element+1);
                    new_factor.add(0);
                    Collections.sort(new_factor);
                    new_factors.add(new_factor);
                }
            }
            
            Collections.sort(new_factors, entryComparator);

            factors = new_factors;

            System.out.println("Level " + level + ":\t");
            printElements(factors);
            System.out.println(" ");
        }


    }
    
    
    public static void printElements(ArrayList<ArrayList<Integer>> factors){

        for(int elementNr = 0; elementNr < factors.size(); ) {
            int count = 1;
            ArrayList<Integer> element = factors.get(elementNr);

            elementNr++;
            while(elementNr < factors.size() && entryComparator.compare(element, factors.get(elementNr)) == 0) {
                elementNr++;
                count++;
            }

            System.out.print(count);
            //System.out.println(" * " + element + ", ");
            System.out.print("\t");
        }

        
    }
    
    public static EntryComparator entryComparator = new EntryComparator();

    public static class EntryComparator implements Comparator<ArrayList<Integer>> {
        public int compare(ArrayList<Integer> left, ArrayList<Integer> right) {
            for(int index = 0; index < left.size(); index++) {
                int compare = left.get(index).compareTo(right.get(index));
                if(compare != 0) return compare;
            }
            return 0;
        }
    }



}
