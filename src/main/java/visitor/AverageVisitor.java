package visitor;

import factory.DataFrame;

public class AverageVisitor implements Visitor {

    private String label;
    private Double result;

    public AverageVisitor(String label) {
        this.label = label;
    }

    public void visit(DataFrame dataFrame, String label) {
        result = dataFrame.average(label);
    }

    public Double getResult() {
        return this.result;
    }

}
