package visitor;

import factory.DataFrame;

public class MaximumVisitor implements Visitor {

    private String label;
    private Double result;

    public MaximumVisitor(String label){
        this.label = label;
    }

    public void visit(DataFrame dataFrame) {
        this.result = dataFrame.max(this.label);
    }

    public Double getResult() {
        return this.result;
    }

}
