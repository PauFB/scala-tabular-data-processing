package visitor

import dataframe.ScalaDataFrame

trait VisitorScala {

  def visit(dataFrame: ScalaDataFrame): Unit

  def getResult[T]: T

}
