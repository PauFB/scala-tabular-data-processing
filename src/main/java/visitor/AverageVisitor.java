package visitor;

import factory.DataFrame;

public class AverageVisitor implements Visitor {

    Double result;

    public void visit(DataFrame dataFrame, String label) {
        result = dataFrame.average(label);
    }

    public Double getResult() {
        return result;
    }
}
