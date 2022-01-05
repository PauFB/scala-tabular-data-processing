package visitor;

import factory.DataFrame;

public class AverageVisitor implements Visitor {

    private String label;
    private Double result;

    public AverageVisitor(String label) {
        this.label = label;
    }

    public void visit(DataFrame dataFrame) {
        this.result = dataFrame.average(this.label);
    }

    public Double getResult() {
        return this.result;
    }

}
