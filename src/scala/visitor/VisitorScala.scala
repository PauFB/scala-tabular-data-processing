package visitor

import composite.{DirectoryScala, FileScala}
import dataframe.ScalaDataFrame

trait VisitorScala {

  def visit(f: FileScala): Unit

  def visit(d: DirectoryScala): Unit

}
