package visitor;

import factory.DataFrame;

public class SumVisitor implements Visitor {

    private String label;
    private Double result;

    public SumVisitor(String label) {
        this.label = label;
    }

    public void visit(DataFrame dataFrame) {
        this.result = dataFrame.sum(this.label);
    }

    public Double getResult() {
        return this.result;
    }

}
