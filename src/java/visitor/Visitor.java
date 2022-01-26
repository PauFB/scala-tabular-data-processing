package visitor;

import composite.DirectoryData;
import composite.FileData;

public interface Visitor {

    void visit(FileData f);

    void visit(DirectoryData d);

}
