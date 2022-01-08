package visitor

import dataframe.ScalaDataFrame
import factory.Data

import java.util.function.Predicate

class FilterVisitor(label: String, predicate: Predicate[String]) extends VisitorScala {

  var result: Data = null

  override def visit(dataFrame: ScalaDataFrame): Unit = {
    this.result = dataFrame.filter(this.label, this.predicate)
  }

  override def getResult[T]: T = result.asInstanceOf[T]

}
