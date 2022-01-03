package visitor;

import factory.DataFrame;

public class MaximumVisitor implements Visitor {

    private Double result;

    public void visit(DataFrame dataFrame, String label) {
        result = dataFrame.max(label);
    }

    public Double getResult() {
        return result;
    }

}
