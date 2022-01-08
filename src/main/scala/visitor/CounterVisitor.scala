package visitor

import dataframe.ScalaDataFrame

class CounterVisitor extends VisitorScala {

  var fileCount: Int = 0
  var directoryCount: Int = 0

  override def visit(dataFrame: ScalaDataFrame): Unit = {
    this.fileCount = dataFrame.fileCount()
    this.directoryCount = dataFrame.directoryCount()
  }

  override def getResult[T]: T = (fileCount + directoryCount).asInstanceOf[T]

}
