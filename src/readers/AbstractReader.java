package readers;

import components.DecisionTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Monika Schrenk on 04.05.16.
 */
public abstract class AbstractReader {

    private String pathToFile;
    DecisionTree tree;

    AbstractReader (String pathToFile, DecisionTree tree) {
        this.pathToFile = pathToFile;
        this.tree = tree;
    }

    public void readFile () throws IOException {
        int currentLine = 0;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            while ((line = br.readLine()) != null) {
                currentLine++;
                String[] data = line.split(";");
                addToTree(data, currentLine);
            }
        }
    }

    abstract void addToTree (String[] data, int currentLine);

}
