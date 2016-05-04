package components;

/**
 * Created by Monika Schrenk on 04.05.16.
 */
public class DecisionTerm implements DecisionInstance {

    private DecisionLiteral firstLiteral;
    private DecisionLiteral secondLiteral;
    private Double value;
    private LogicalOperation operation;

    public DecisionTerm(DecisionLiteral firstLiteral, DecisionLiteral secondLiteral, LogicalOperation operation) {
        this.firstLiteral = firstLiteral;
        this.secondLiteral = secondLiteral;
        this.operation = operation;
    }

    @Override
    public String getName() {
        return firstLiteral.getName() + " " + operation + " " + secondLiteral.getName();
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getName() + " (" + (value == null ? "?" : value)  + ")";
    }

    public LogicalOperation getOperation() {
        return operation;
    }

    public DecisionLiteral getFirstLiteral() {
        return firstLiteral;
    }

    public DecisionLiteral getSecondLiteral() {
        return secondLiteral;
    }

}
