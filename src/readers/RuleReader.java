package readers;

import components.DecisionLiteral;
import components.DecisionInstance;
import components.DecisionTree;


import java.security.InvalidParameterException;

/**
 * Created by Monika Schrenk on 04.05.16.
 */
public class RuleReader extends AbstractReader{

    public RuleReader (String pathToFile, DecisionTree tree) {
        super(pathToFile, tree);
    }

    @Override
    void addToTree (String[] data, int currentLine) {

        // Length test for rule, must have premise, conclusion and value
        if (data.length != 3) {
            throw new InvalidParameterException("Wrong number of arguments in line " + currentLine + " (must contain premise; conclusion; value).");
        }

        // Split premise into literals and operation
        String[] premiseStrings = data[0].split(" ");

        // length test for premise, must be either literal or binary operation if not existing
        if (premiseStrings.length != 1 && premiseStrings.length != 3) {
            throw new InvalidParameterException("Wrong number of arguments in line " + currentLine + " (premise must contain literal operator literal).");
        }

        // create literal or binary premise and store in tree
        DecisionInstance premise;
        if (premiseStrings.length == 1) {
            premise = tree.createOrFindLiteral(premiseStrings[0]);
        } else {
            premise = tree.createDecisionObject(premiseStrings[0], premiseStrings[1], premiseStrings[2]);
        }

        // create conclusion and store in tree if not existing
        String conclusionString = data[1].trim();
        DecisionLiteral conclusion = tree.createOrFindLiteral(conclusionString);

        // parse factor
        double factor = Double.parseDouble(data[2].trim());

        // add rule to decision tree
        tree.addRule(premise, conclusion, factor);
    }

}
