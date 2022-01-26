package visitor;

import composite.DirectoryData;
import composite.FileData;
import factory.DataFrame;

public class MinimumVisitor implements Visitor {

    private final String label;
    private Double result = Double.MAX_VALUE;

    public MinimumVisitor(String label) {
        this.label = label;
    }

    public void visit(FileData f) {
        int labelIndex = f.getLabelList().indexOf(label);

        if (labelIndex != -1) {
            try {
                for (String elem : f.getContent().get(labelIndex)) {
                    result = Math.min(Double.parseDouble(elem), result);
                }
            } catch (NumberFormatException e) {
                result = null;
            }
        } else {
            result = null;
        }
    }

    public void visit(DirectoryData d) {
        for (DataFrame child : d.getChildren()) {
            child.accept(this);
        }
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

}
