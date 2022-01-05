package visitor;

import factory.DataFrame;

public interface Visitor {

    void visit(DataFrame dataFrame);

    Double getResult();

}
