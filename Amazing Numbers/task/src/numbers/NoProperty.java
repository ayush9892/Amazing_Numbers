package numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class NoProperty {

    boolean even, odd, buzz, duck, palindromic, gap, spy, square, sunny, jumping, happy, sad;
    ArrayList<String> avaProp = new ArrayList<>(12);
    Map<String, String> mutuallyExc = new HashMap<>();

    public NoProperty() {
        avaProp.add("buzz");
        avaProp.add("duck");
        avaProp.add("palindromic");
        avaProp.add("gapful");
        avaProp.add("spy");
        avaProp.add("square");
        avaProp.add("sunny");
        avaProp.add("jumping");
        avaProp.add("happy");
        avaProp.add("sad");
        avaProp.add("even");
        avaProp.add("odd");
        mutuallyExc.put("even", "odd");
        mutuallyExc.put("odd", "even");
        mutuallyExc.put("sunny", "square");
        mutuallyExc.put("square", "sunny");
        mutuallyExc.put("duck", "spy");
        mutuallyExc.put("spy", "duck");
        mutuallyExc.put("happy", "sad");
        mutuallyExc.put("sad", "happy");
    }


    private boolean select(int ind) {
        switch (ind) {
            case 0:
                return buzz;
            case 1:
                return duck;
            case 2:
                return palindromic;
            case 3:
                return gap;
            case 4:
                return spy;
            case 5:
                return square;
            case 6:
                return sunny;
            case 7:
                return jumping;
            case 8:
                return happy;
            case 9:
                return sad;
            case 10:
                return even;
            case 11:
                return odd;
            default:
                return false;
        }
    }

    public int naturalNoCheck(BigInteger no) {
        if(no.signum() == 0) {
            return 0;
        } else if(no.signum() == -1) {
            return -1;
        }
        return 1;
    }

    public void Properties(BigInteger no, int rng, String... props) {
        boolean flag = true;
        while(rng != 0) {
            properties(no);
            try {
                for(String prop: props) {
                    if(prop.charAt(0) == '-') {
                        if(select(avaProp.indexOf(prop.substring(1).toLowerCase()))) {
                            flag = false;
                            break;
                        }
                    } else {
                        if(!select(avaProp.indexOf(prop.toLowerCase()))){
                            flag = false;
                            break;
                        }
                    }
                }
            } catch (NullPointerException e) {}


            if(flag) {
                resultsDisplay(no);
                rng--;
            }
            no = no.add(BigInteger.ONE);
            reinitialize();
            flag = true;
        }
    }

    public void resultsDisplay(BigInteger no) {
        ArrayList<String> numProp = new ArrayList<>();
        for(int i=0; i<12; i++) {
            if(select(i)) {
                numProp.add(avaProp.get(i));
            }
        }
        System.out.print("\t" + no +" is ");
        for(int i=0; i<numProp.size(); i++) {
            System.out.print(numProp.get(i));
            if(i != numProp.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        reinitialize();
    }

    public void properties(BigInteger no) {

        String num = no.toString();
        String s2 = String.valueOf(num.charAt(num.length() - 1));
        if (Integer.parseInt(s2) % 2 == 0) {
            even = true;
        } else {
            odd = true;
        }

        if (Integer.parseInt(s2) == 7 || no.remainder(BigInteger.valueOf(7L)).toString().equals("0")) {
            buzz = true;
        }

        if (num.substring(1).contains("0")) {
            duck = true;
        }

        if(new StringBuilder(num).reverse().toString().equals(no.toString())) {
            palindromic = true;
        }

        if(num.length() > 2 && no.remainder(new BigInteger(num.charAt(0) + s2)).toString().equals("0")) {
            gap = true;
        }

        BigInteger ad = BigInteger.valueOf(0);
        BigInteger mul = BigInteger.valueOf(1);
        for(int i=0; i<num.length(); i++) {
            BigInteger val = new BigInteger(String.valueOf(num.charAt(i)));
            ad = ad.add(val);
            mul = mul.multiply(val);
        }
        if(ad.equals(mul)) {
            spy = true;
        }

        if(sqrtCheck(no.add(BigInteger.ONE))) {
            sunny = true;
        }

        if(sqrtCheck(no)) {
            square = true;
        }

        boolean jumpFlag = true;
        for(int i=0; i<num.length()-1; i++) {
            int preNum = Integer.parseInt(String.valueOf(num.charAt(i)));
            int sucNum = Integer.parseInt(String.valueOf(num.charAt(i+1)));

            if(Math.abs(preNum-sucNum) != 1) {
                jumpFlag = false;
            }
        }
        if(jumpFlag) {
            jumping = true;
        }

        int final_no = 0;
        while(final_no != 1 && final_no != 4) {
            final_no = 0;
            for(int i=0; i<num.length(); i++) {
                int char_no = Integer.parseInt(String.valueOf(num.charAt(i)));
                final_no += Math.pow(char_no, 2);
            }
            num = String.valueOf(final_no);
        }
        if(final_no == 1) {
            happy = true;
        } else {
            sad = true;
        }
    }

    private boolean sqrtCheck(BigInteger No) {
        BigInteger sqrtCheck = No.sqrt();
        sqrtCheck = sqrtCheck.multiply(sqrtCheck);
        return sqrtCheck.equals(No);
    }

    public void resultDisplay(BigInteger no) {
        System.out.println("Properties of " + no);
        for(int i=0; i<12; i++) {
            System.out.println("\t " + avaProp.get(i) + ": " + select(i));
        }
        reinitialize();
    }

    private void reinitialize() {
        even = false;
        odd = false;
        buzz = false;
        duck = false;
        palindromic = false;
        gap = false;
        spy = false;
        sunny = false;
        square = false;
        jumping = false;
        happy = false;
        sad = false;
    }
}
