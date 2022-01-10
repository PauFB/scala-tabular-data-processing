package visitor;

import factory.DataFrame;

public class MaximumVisitor implements Visitor {

    private final String label;
    private Double result;

    public MaximumVisitor(String label) {
        this.label = label;
    }

    public void visit(DataFrame dataFrame) {
        result = dataFrame.max(label);
    }

    public Double getResult() {
        return result;
    }

}
