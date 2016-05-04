package components;


/**
 * Created by Monika Schrenk on 04.05.16.
 */
public class DecisionRule {

    private DecisionInstance premise;
    private DecisionLiteral conclusion;
    private Double value;

    public DecisionRule (DecisionInstance premise, DecisionLiteral conclusion, Double value) {
        this.premise = premise;
        this.conclusion = conclusion;
        this.value = value;
    }

    @Override
    public String toString() {
        return premise.getName() + " -> " + conclusion.getName() + " (" + (value == null ? "?" : value) + ")";
    }

    public DecisionInstance getPremise() {
        return premise;
    }

    public DecisionLiteral getConclusion() {
        return conclusion;
    }

    public double getValue() {
        return value;
    }
}
