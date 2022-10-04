package numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInput {
    Scanner sc = new Scanner(System.in);
    NoProperty numPop;
    BigInteger bInt;
    String no = "-1";
    int range;
    String property1, property2;
    String[] propList;

    public UserInput(NoProperty numPop) {
        this.numPop = numPop;
    }

    private boolean PropertyCheck(String ... property) {
        boolean stu = true;
        int count = 0;
        String s = "";
        String statement = "";
        for(String st: property) {
            if(st.charAt(0) == '-') {
                st = st.substring(1);
            }
           if(!numPop.avaProp.contains(st.toLowerCase())) {
               stu = false;
               statement += s;
               s = ", ";
               statement += st.toUpperCase();
               count++;
           }
        }
        if(!stu) {
            if(count > 1) {
                System.out.println("The properties [" + statement + "] are wrong.");
            } else{
                System.out.println("The property [" + statement + "] is wrong.");
            }
            System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
        }
        return stu;
    }

    private void LenOne() {
        System.out.println();
    }
   private boolean InpLengths(String[] Params) {
        LenOne();
        range = Integer.parseInt(Params[1]);
        if(range < 0) {
            System.out.println("The second parameter should be a natural number");
            return false;
        }
        if(Params.length > 2) {
            propList = Arrays.copyOfRange(Params, 2, Params.length);
             if(PropertyCheck(propList)) {
                 return MutuallyExcCheck(propList);
             } else {
                 return false;
             }
        }
        return true;
    }


   private boolean MutuallyExcCheck(String[] props) {
       ArrayList<String> propList = new ArrayList<>();
       for(String prop: props) {
           prop = prop.toLowerCase();
           propList.add(prop);
       }

       for(String prop: propList) {
            if(prop.charAt(0) == '-') {
                String PosProp = prop.substring(1);
                if(propList.contains(PosProp)) {
                    property1 = prop;
                    property2 = PosProp;
                    printError();
                    return false;
                }
                if(numPop.mutuallyExc.containsKey(PosProp)) {
                    String chk = numPop.mutuallyExc.get(PosProp);
                    if(propList.contains("-" + chk)) {
                        property1 = prop;
                        property2 = "-" + chk;
                        printError();
                        return false;
                    }
                }
            }
            if(numPop.mutuallyExc.containsKey(prop)) {
                String chk = numPop.mutuallyExc.get(prop);
                if(propList.contains(chk)) {
                    property1 = prop;
                    property2 = chk;
                    printError();
                    return false;
                }
            }
        }
      return true;
    }


    private void printError() {
        System.out.print("The request contains mutually exclusive properties: [");
        System.out.println(property1.toUpperCase() + ", " + property2.toUpperCase() + "]");
        System.out.println("There are no numbers with these properties.");
    }

    public void TakingInput(EntryDisplay Entry) {
        System.out.println("Welcome to Amazing Numbers!");
        Entry.Display();
        while (!no.equals("0")) {
            System.out.print("\nEnter a request: ");
            no = sc.nextLine();
            String[] nos = no.split(" ");
            if (no.equals("")) {
                Entry.Display();
            } else {
                try {
                    bInt = new BigInteger(nos[0]);
                    if (numPop.naturalNoCheck(bInt) == 0) {
                        System.out.println("Goodbye!");
                    } else if (numPop.naturalNoCheck(bInt) == -1) {
                        System.out.println("The first parameter should be a natural number or zero.");
                    } else {
                        if (nos.length == 1) {
                            LenOne();
                            numPop.properties(bInt);
                            numPop.resultDisplay(bInt);
                        } else {
                            if(InpLengths(nos)) {
                                numPop.Properties(bInt, range, propList);
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
        }
        sc.close();
    }

}
