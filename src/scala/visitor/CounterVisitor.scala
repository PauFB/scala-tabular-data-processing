package visitor

import composite.{DirectoryScala, FileScala}
import dataframe.ScalaDataFrame

class CounterVisitor extends VisitorScala {

  var fileCount: Int = 0
  var directoryCount: Int = 0

  override def visit(f: FileScala): Unit = {
    fileCount += 1
  }

  override def visit(d: DirectoryScala): Unit = {
    directoryCount += 1
    for (child <- d.getChildren) {
      child.accept(this)
    }
  }

  //def getResult[T]: T = (fileCount + directoryCount).asInstanceOf[T]

  def setResult(fileCount: Int, directoryCount: Int): Unit = {
    this.fileCount = fileCount
    this.directoryCount = directoryCount
  }

}
