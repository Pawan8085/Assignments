public class Question1{

    /* Question :- Programming Language Assignment:
You are given a list of clothes at a store. The clothes have the following prices:
  T-shirt: Rs. 50.00
  Jeans: Rs. 80.00
  Jacket: Rs. 100.00
If a person has a budget of Rs. 600, how many T-shirts, jeans, and jackets can they purchase?

Write a well-structured and efficient program that finds and prints all possible combinations of these items with the following conditions:

  1) You must purchase at least one item of each type of clothing (T-shirt, jeans, and jacket).
  2) The remaining balance after purchasing the items in each combination should be less
than the cost of the least-priced item (i.e., less than Rs. 50.00 for a T-shirt).
 */
    public static void main(String[] args) {

        // Time Complexity O(N*N)  though we are using 3 loops but inner loops are running with rest budgetPrice we are not running for entire budget 
        // Space Complexity O(1)
        
        double budget = 600;
        double tShirtPrice = 50;
        double jeanPrice   = 80;   
        double jacketPrice = 100;

        int totalCombination = 0;

        // left money after buying Tshirt
        double leftMoney1 = 0;
        for(int tshirt = 1; tshirt*tShirtPrice <= budget; ++tshirt){
            leftMoney1 = tshirt*tShirtPrice;

            // left money after buying Jeans
            double leftMoney2 = 0;
            for(int jeans = 1; jeans*jeanPrice <= budget-leftMoney1; ++jeans){

                leftMoney2 = jeans*jeanPrice;
                for(int jacket = 1; jacket*jacketPrice <= budget-leftMoney2; ++jacket){

                    double cost = tshirt*tShirtPrice + jeans*jeanPrice + jacket*jacketPrice;

                    if(cost <= budget && cost >= tShirtPrice + jeanPrice + jacketPrice){
                        totalCombination++;
                       System.out.println("Combination No "+ totalCombination +" -> Tshirt " + tshirt + ", Jeans "+ jeans +", "+"Jacket "+jacket); 
                    }
                }
            }                
               
        }

        System.out.println("TotalCombinations "+totalCombination);
        
    }
}