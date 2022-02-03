package visitor;

import composite.DirectoryData;
import composite.FileData;
import factory.DataFrame;

import java.util.ArrayList;

public class AverageVisitor implements Visitor {

    private final String label;
    private Double result;

    public AverageVisitor(String label) {
        this.label = label;
    }


    public void visit(FileData f) {
        double sum = 0;
        int labelIndex = f.getLabelList().indexOf(label);

        if (labelIndex != -1) {
            try {
                for (String elem : f.getContent().get(labelIndex)) {
                    sum += Double.parseDouble(elem);
                }
                result = sum / f.getContent().get(labelIndex).size();
            } catch (NumberFormatException e) {
                result = null;
            }
        } else {
            result = null;
        }
    }

    public void visit(DirectoryData d) {
        ArrayList<String> list;
        double accumulator = 0.0;
        int nElements = 0;
        for (DataFrame child : d.getChildren()) {                      // For every child
            list = child.getColumn(label);
            if (list != null) {
                for (String value : list) {
                    accumulator += Double.parseDouble(value);     // Accumulate the elements of the column indexed by label
                    nElements++;
                }
            }
        }
        if (nElements != 0) {
            result = accumulator / nElements;
        } else {
            result = null;
        }
    }

    public Double getResult() {
        return result;
    }

}
