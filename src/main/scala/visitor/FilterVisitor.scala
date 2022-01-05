package visitor

import dataframe.DataFrameTrait
import factory.Data

import java.util.function.Predicate

class FilterVisitor(label: String, predicate: Predicate[String]) extends VisitorTrait {

  var result: Data = null

  override def visit(dataFrame: DataFrameTrait): Unit = {
    this.result = dataFrame.filter(this.label, this.predicate)
  }

  override def getResult[T](): T = this.result.asInstanceOf[T]

}
