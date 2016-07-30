import components.*;
import readers.LiteralReader;
import readers.RuleReader;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Monika Schrenk on 04.05.16.
 */
public class App {

    private DecisionTree tree;
    private DecisionLiteral target;
    private Scanner sc;

    private App() {
        tree = new DecisionTree();
        sc = new Scanner(System.in);
    }

    public void readLiteralsAndRules() throws IOException {
        System.out.print("Please enter path to file containing literals: ");
        String pathToLiterals = sc.nextLine();
        LiteralReader lr = new LiteralReader(pathToLiterals, tree);
        lr.readFile();

        System.out.print("Please enter path to file containing rules: ");
        String pathToRules = sc.nextLine();
        RuleReader rr = new RuleReader(pathToRules, tree);
        rr.readFile();
    }

    public void printLiteralsAndRules(String title) {
        System.out.println();
        System.out.println(title + ":");
        System.out.println("Literals:\t" + tree.getLiterals());
        System.out.println("Binary operations:\t" + tree.getObjects());
        System.out.println("Rules:\t" + tree.getRules());
        System.out.println();
    }

    public void specifyTarget() {
        System.out.print("Please enter target literal: ");
        String targetName  = sc.nextLine();
        target = tree.findLiteralByName(targetName);
        if (target == null) {
            System.out.println("Cannot find literal " + targetName + " in my list, please enter an existing literal!");
            specifyTarget();
        } else {
            System.out.println("Successfully set target to " + targetName);
        }
        System.out.println();
    }

    public void calculateAndPrintResult() {
        System.out.println("RESULT: " + new DecisionCalculator(tree, target).calculate());
    }

    public static void main(String[] args) {

        App app = new App();

        try {
            app.readLiteralsAndRules();
            app.printLiteralsAndRules("INPUT");
            app.specifyTarget();
            app.calculateAndPrintResult();
            app.printLiteralsAndRules("OUTPUT");
        } catch (IOException e) {
            System.out.println ("File not found!");
        }

    }
}
