package visitor;

import factory.DataFrame;

public class SumVisitor implements Visitor {

    private final String label;
    private Double result;

    public SumVisitor(String label) {
        this.label = label;
    }

    public void visit(DataFrame dataFrame) {
        result = dataFrame.sum(label);
    }

    public Double getResult() {
        return result;
    }

}
