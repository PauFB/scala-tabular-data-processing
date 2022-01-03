package visitor;

import factory.DataFrame;

public class SumVisitor implements Visitor {

    private Double result;

    public void visit(DataFrame dataFrame, String label) {
        result = dataFrame.sum(label);
    }

    public Double getResult() {
        return result;
    }

}
