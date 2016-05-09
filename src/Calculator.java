import components.*;

import java.util.ArrayList;

    /**
    * Created by Monika Schrenk on 04.05.16.
    */
public class Calculator {

    private DecisionTree tree;
    private DecisionLiteral target;

    public Calculator(DecisionTree tree, DecisionLiteral target) {
        this.tree = tree;
        this.target = target;
    }

    public Double calculate() {
        return calculateFactor(target);
    }

    // Iterates from target instance backwards, calculating factors of all instances
    private Double calculateFactor(DecisionInstance instance) {

        Double result;

        // Find out if currently evaluating a literal or a term
        if (instance instanceof DecisionLiteral) {
            DecisionLiteral lit = (DecisionLiteral) instance;
            ArrayList<DecisionRule> currentRules = tree.findRulesWithConclusion(lit);

            // literal with no preceding implication
            if (currentRules.size() == 0) {
                result = lit.getValue();
            }  else { // literal preceded by 1 or more implications
                // single implication is covered by n implications method
                result = calculateParallel(currentRules);
            }
        } else {
            // evaluate decision term, depending on the logical operation
            DecisionTerm term = (DecisionTerm)instance;
            result = calculateValue(term);
        }

        // store for output values
        instance.setValue(result);

        return result;
    }

        // Calculates factor of terms (rules for AND and OR)
    private Double calculateValue (DecisionTerm term) {
        DecisionLiteral l1 = term.getFirstLiteral();
        DecisionLiteral l2 = term.getSecondLiteral();
        switch (term.getOperation()) {
            case AND:
                return Math.min(calculateFactor(l1), calculateFactor(l2));
            case OR:
                return Math.max(calculateFactor(l1), calculateFactor(l2));
        }
        throw new UnsupportedOperationException("No rule for operation: " + term.getOperation());
    }

    // Calculates factor of an implication
    private Double calculateSerial(DecisionRule rule) {
        return rule.getValue() * Math.max(0, calculateFactor(rule.getPremise()));
    }

    // Calculates factor of n parallel implications
    private Double calculateParallel (ArrayList<DecisionRule> rules) {
        if (rules.size() == 1) {
            return calculateSerial(rules.get(0));
        }
        DecisionRule r = rules.get(rules.size()-1);
        rules.remove(rules.size()-1);
        return parallelFunction(calculateParallel(rules), calculateSerial(r));
    }

    // Helper function for parallel implication calcalation
    private Double parallelFunction(Double x, Double y) {
        if (x > 0 && y > 0) {
            return x + y - x * y;
        }
        if (x < 0 && y < 0) {
            return x + y + x * y;
        }
        return x + y / (1 - Math.min(Math.abs(x), Math.abs(y)));
    }
}
