package components;

/**
 * Created by Monika Schrenk on 04.05.16.
 */
public class DecisionLiteral implements DecisionInstance {

    private String name;
    private Double value;

    public DecisionLiteral (String name, Double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {

        return name + " (" + (value == null ? "?" : value) + ")";
    }
}
