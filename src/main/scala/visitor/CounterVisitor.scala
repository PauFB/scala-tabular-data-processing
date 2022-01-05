package visitor

import dataframe.DataFrameTrait

class CounterVisitor extends VisitorTrait {

  var fileCount: Int = 0
  var directoryCount: Int = 0

  override def visit(dataFrame: DataFrameTrait): Unit = {
    this.fileCount = dataFrame.fileCount()
    this.directoryCount = dataFrame.directoryCount()
  }

  override def getResult[T](): T = (this.fileCount + this.directoryCount).asInstanceOf[T]

}
