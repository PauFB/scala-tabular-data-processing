package visitor;

import factory.DataFrame;

public class MinimumVisitor implements Visitor {

    private String label;
    private Double result;

    public MinimumVisitor(String label) {
        this.label = label;
    }

    public void visit(DataFrame dataFrame) {
        this.result = dataFrame.min(this.label);
    }

    public Double getResult() {
        return this.result;
    }

}
