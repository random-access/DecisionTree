package components;

import java.util.ArrayList;

/**
 * Created by Monika Schrenk on 04.05.16.
 */
public class DecisionTree {

    private ArrayList<DecisionLiteral> literals;
    private ArrayList<DecisionInstance> objects;
    private ArrayList<DecisionRule> rules;

    public DecisionTree() {
        this.literals = new ArrayList<>();
        this.objects = new ArrayList<>();
        this.rules = new ArrayList<>();
    }

    public DecisionLiteral findLiteralByName(String name) {
        for (DecisionLiteral l : literals) {
            if (l.getName().equals(name)) {
                return l;
            }
        }
        return null;
    }

    public DecisionLiteral createOrFindLiteral(String name) {
        DecisionLiteral premise = findLiteralByName(name);
        if (premise == null) {
            premise = new DecisionLiteral(name, null);
            literals.add(premise);
        }
        return premise;
    }

    public DecisionInstance createDecisionObject(String a, String op, String b) {
        DecisionLiteral l1 = createOrFindLiteral(a);
        DecisionLiteral l2 = createOrFindLiteral(b);
        DecisionInstance o = new DecisionTerm(l1, l2, LogicalOperation.valueOf(op.trim()));
        objects.add(o);
        return o;
    }

    public ArrayList<DecisionRule> findRulesWithConclusion(DecisionInstance o) {
        ArrayList<DecisionRule> l = new ArrayList<>();
        for (DecisionRule r : rules) {
            if (o == r.getConclusion()) {
                l.add(r);
            }
        }
        return l;
    }

    public ArrayList<DecisionLiteral> getLiterals() {
        return literals;
    }

    public ArrayList<DecisionInstance> getObjects() {
        return objects;
    }

    public ArrayList<DecisionRule> getRules() {
        return rules;
    }

    public void addLiteral(String name, Double value) {
        literals.add(new DecisionLiteral(name, value));
    }

    public void addRule(DecisionInstance premise, DecisionLiteral conclusion, Double value) {
        rules.add(new DecisionRule(premise, conclusion, value));
    }
}
