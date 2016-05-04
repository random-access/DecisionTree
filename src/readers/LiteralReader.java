package readers;

import components.DecisionTree;

import java.security.InvalidParameterException;

/**
 * Created by Monika Schrenk on 04.05.16.
 */
public class LiteralReader extends AbstractReader{

    public LiteralReader(String pathToFile, DecisionTree tree) {
        super(pathToFile, tree);
    }

    void addToTree (String[] data, int currentLine) {

        // Each line must contain literal name and value
        if (data.length != 2) {
            throw new InvalidParameterException("Wrong number of arguments in line " + currentLine + " (must contain name; value).");
        }

        // parse name and value
        String name = data[0].trim();
        double factor = Double.parseDouble(data[1].trim());

        // add literal to decision tree
        tree.addLiteral(name, factor);
    }

}
