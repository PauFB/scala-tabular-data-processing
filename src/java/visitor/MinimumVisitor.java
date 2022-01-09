package visitor;

import factory.DataFrame;

public class MinimumVisitor implements Visitor {

    private final String label;
    private Double result;

    public MinimumVisitor(String label) {
        this.label = label;
    }

    public void visit(DataFrame dataFrame) {
        result = dataFrame.min(label);
    }

    public Double getResult() {
        return result;
    }

}
