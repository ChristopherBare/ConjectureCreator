package conjecturecreator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author christopher1
 */
public final class EquationParser extends ConjectureCreator {

    ArrayList<Character> postfixtokens = new ArrayList<>();
    char token;
    BigInteger number = new BigInteger("0");
    String postfixtokensString;

    public EquationParser(String input, BigInteger n, boolean type) {

        String postfix = infixToPostfix(input);

        TokenizerPostfix(postfix);
        //EvaluateToBigInt(postfixtokensString);

    }

    public void TokenizerPostfix(String input) {
        StringTokenizer equation = new StringTokenizer(input, "");
        while (equation.hasMoreTokens()) {
            System.out.println(equation.nextToken());
            token = equation.nextToken().charAt(0);
            postfixtokens.add(token);
            postfixtokens.add(' ');
        }
        StringBuilder builder = new StringBuilder(postfixtokens.size());
        for (Character ch : postfixtokens) {
            builder.append(ch);
        }
        postfixtokensString = builder.toString();
    }
//Using shunting-yard to convert infix to postfix so that it can be parsed and converted to a biginteger experession 
    //for use in the setting of equations by the user.

    static String infixToPostfix(String infix) {
        final String ops = "-+/*^";
        StringBuilder sb = new StringBuilder();
        Stack<Integer> s = new Stack<>();

        for (String token : infix.split("\\s")) {
            if (token.isEmpty()) {
                continue;
            }
            char c = token.charAt(0);
            int idx = ops.indexOf(c);

            // check for operator
            if (idx != -1) {
                if (s.isEmpty()) {
                    s.push(idx);
                } else {
                    while (!s.isEmpty()) {
                        int prec2 = s.peek() / 2;
                        int prec1 = idx / 2;
                        if (prec2 > prec1 || (prec2 == prec1 && c != '^')) {
                            sb.append(ops.charAt(s.pop())).append(' ');
                        } else {
                            break;
                        }
                    }
                    s.push(idx);
                }
            } else if (c == '(') {
                s.push(-2); // -2 stands for '('
            } else if (c == ')') {
                // until '(' on stack, pop operators.
                while (s.peek() != -2) {
                    sb.append(ops.charAt(s.pop())).append(' ');
                }
                s.pop();
            } else {
                sb.append(token).append(' ');
            }
        }
        while (!s.isEmpty()) {
            sb.append(ops.charAt(s.pop())).append(' ');
        }
        return sb.toString();
    }


    public static void evalPostfix(String infix) {
        String cleanExpr = cleanExpr(infix);
        LinkedList<BigInteger> stack = new LinkedList<BigInteger>();
        

       
        System.out.println("Input\tOperation\tStack after");
        for (String token : cleanExpr.split("\\s")) {
            System.out.print(token + "\t");
            BigInteger tokenNum = null;
            try {

                tokenNum = new BigInteger(token + "");
            } catch (NumberFormatException e) {
            }
            if (tokenNum != null) {
                System.out.print("Push\t\t");
                stack.push(tokenNum);
            } else if (token.equals("*")) {
                System.out.print("Operate\t\t");
                BigInteger secondOperand = stack.pop();
                BigInteger firstOperand = stack.pop();
                stack.push(firstOperand.multiply(secondOperand));
            } else if (token.equals("/")) {
                System.out.print("Operate\t\t");
                BigInteger secondOperand = stack.pop();
                BigInteger firstOperand = stack.pop();
                stack.push(firstOperand.divide(secondOperand));
            } else if (token.equals("-")) {
                System.out.print("Operate\t\t");
                BigInteger secondOperand = stack.pop();
                BigInteger firstOperand = stack.pop();
                stack.push(firstOperand.subtract(secondOperand));
            } else if (token.equals("+")) {
                System.out.print("Operate\t\t");
                BigInteger secondOperand = stack.pop();
                BigInteger firstOperand = stack.pop();
                stack.push(firstOperand.add(secondOperand));
            } else if (token.equals("^")) {
                System.out.print("Operate\t\t");
                BigInteger secondOperand = stack.pop();
                BigInteger firstOperand = stack.pop();
                stack.push(firstOperand.pow(secondOperand.intValue()));
            } else {//just in case
                System.out.println("Error");
                return;
            }
            System.out.println(stack);
        }
        System.out.println("Final answer: " + stack.pop());
    }

    private static String cleanExpr(String expr) {
        //remove all non-operators, non-whitespace, and non digit chars
        return expr.replaceAll("[^\\^\\*\\+\\-\\d/\\s]", "");
    }

}
