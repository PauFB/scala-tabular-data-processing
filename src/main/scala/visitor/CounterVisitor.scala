package visitor

import dataframe.DataFrameTrait

class CounterVisitor extends VisitorTrait {

  var result: Int = 0

  override def visit(dataFrame: DataFrameTrait): Unit = {
    result = dataFrame.count()
  }

  override def getResult[T](): T = result.asInstanceOf[T]

}
