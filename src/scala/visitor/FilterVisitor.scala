package visitor

import composite.{DirectoryScala, FileScala}
import dataframe.ScalaDataFrame
import factory.Data

import java.util
import java.util.{ArrayList, LinkedList}
import java.util.function.Predicate

class FilterVisitor(label: String, predicate: String => Boolean) extends VisitorScala {

  var result: Data = new Data(new util.LinkedList[String], new util.LinkedList[util.ArrayList[String]])

  override def visit(f: FileScala): Unit = {
    val col: Int = f.getLabelList.indexOf(label)
    val newContent: util.LinkedList[java.util.ArrayList[String]] = new java.util.LinkedList[util.ArrayList[String]]
    var newData: Data = null

    //Initialize result with labelList and empty content
    if (result.getContent.isEmpty) {
      val iniContent: util.LinkedList[java.util.ArrayList[String]] = new java.util.LinkedList[util.ArrayList[String]]
      for (k <- 0 until f.columns()) {
        iniContent.add(new util.ArrayList[String])
      }
      result = new Data(f.getLabelList, iniContent)
    }

    if (col != -1) {
      val filtered_column: List[String] = f.getContent(col).filter(predicate) // Filter the column indexed by label
      if (filtered_column.nonEmpty) {
        for (k <- 0 until f.columns()) {
          newContent.add(new util.ArrayList[String])
        }
        for (j <- 0 until f.size()) { // For every line
          if (filtered_column.contains(f.getContent(col)(j))) { // If an element in the original column also exists in filtered_column
            for (i <- 0 until f.columns()) {
              newContent.get(i).add(f.getContent(i)(j))  // Add it to newContent
            }
          }
        }
        newData = new Data(f.getLabelList, newContent)
        for (i <- 0 until newData.getContent.size) {
          result.getContent.get(i).addAll(newData.getContent.get(i)) //Add to the content of result the content of the rest of filters
        }
      }
    }
  }

  override def visit(d: DirectoryScala): Unit = {
    for (child <- d.getChildren) {
      child.accept(this)
    }
  }

  def getResult: Data = result

  def setResult(data: Data): Unit = {
    result = data
  }

}
