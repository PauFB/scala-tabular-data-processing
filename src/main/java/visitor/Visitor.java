package visitor;

import factory.DataFrame;

public interface Visitor {

    void visit(DataFrame dataFrame, String label);

    Double getResult();

}
