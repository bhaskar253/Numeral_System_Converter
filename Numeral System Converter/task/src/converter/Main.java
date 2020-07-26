package converter;

import java.util.Scanner;

class Num {
    long intPart;
    double fracPart;
    String intString;
    String fracString;
    boolean isToOne;
    boolean isWithFrac;
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int radixOfNum = sc.nextInt();
            String numInString = sc.next();
            int radixTarget = sc.nextInt();

            if (radixOfNum < 1 || radixTarget < 1 || radixOfNum > 36 || radixTarget > 36)
                throw new Exception();
            int roundUpFive = 5;
            Num num = new Num();

            if (numInString.contains(".")) {
                num.isWithFrac = true;
                int point = numInString.indexOf(".");
                num.intString = numInString.substring(0, point);
                num.fracString = numInString.substring(point + 1);
            } else {
                num.intString = numInString;
            }

            if (radixOfNum == 1) {
                num.intPart = num.intString.length();
            } else {
                num.intPart = Long.parseLong(num.intString, radixOfNum);
            }

            if (radixTarget == 1) {
                num.isToOne = true;
                for (int i = 0; i < num.intPart; i++) {
                    System.out.print(1);
                }
            } else {
                String intTarget = Long.toString(num.intPart, radixTarget);
                System.out.print(intTarget);
            }

            if (!num.isToOne && num.isWithFrac) {
                char[] digits = num.fracString.toCharArray();
                StringBuilder fracTarget = new StringBuilder();
                fracTarget.append(".");

                for (int i = 0; i < digits.length; i++) {
                    double partOfFrac =
                            Character.digit(digits[i], radixOfNum)
                                    / Math.pow(radixOfNum, i + 1);
                    num.fracPart += partOfFrac;
                }

                for (int i = 0; i < roundUpFive; i++) {
                    double fracToTargetRadix = num.fracPart * radixTarget;
                    long intPartOfFrac = (long) fracToTargetRadix;
                    String intPartOfFracStr = Long.toString(intPartOfFrac, radixTarget);
                    num.fracPart = fracToTargetRadix - intPartOfFrac;
                    fracTarget.append(intPartOfFracStr);
                }

                System.out.print(fracTarget.toString());
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}