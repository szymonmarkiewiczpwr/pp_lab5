package org.plecak;

public class Main {
    public static void main(String[] args) {
        Problem problem = new Problem(3, 10);

        ProblemResult result = problem.solve(4);

        System.out.println(result.toString());
    }
}