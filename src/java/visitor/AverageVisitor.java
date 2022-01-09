package visitor;

import factory.DataFrame;

public class AverageVisitor implements Visitor {

    private final String label;
    private Double result;

    public AverageVisitor(String label) {
        this.label = label;
    }

    public void visit(DataFrame dataFrame) {
        result = dataFrame.average(label);
    }

    public Double getResult() {
        return result;
    }

}
