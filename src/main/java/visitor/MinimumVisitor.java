package visitor;

import factory.DataFrame;

public class MinimumVisitor implements Visitor {

    private Double result;

    public void visit(DataFrame dataFrame, String label) {
        result = dataFrame.min(label);
    }

    public Double getResult() {
        return result;
    }
}
