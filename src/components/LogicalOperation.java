package components;

/**
 * Created by Monika Schrenk on 04.05.16.
 */
public enum LogicalOperation {

    AND {
        @Override
        public Double calculateValue(Double x, Double y) {
            return Math.min(x, y);
        }
    }, OR {
        @Override
        public Double calculateValue(Double x, Double y) {
            return Math.max(x, y);
        }
    };

    public abstract Double calculateValue(Double x, Double y);
}
