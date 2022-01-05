package composite

import dataframe.DataFrameTrait
import factory.Data
import visitor.VisitorTrait

import java.util
import java.util.function.Predicate

class FileScala(filePath: String) extends DataFrameTrait {

  val fileDataFrame = new FileCOMP(filePath)

  def at(id: Int, label: String): String = {
    return this.fileDataFrame.at(id, label)
  }

  def columns(): Int = {
    return fileDataFrame.columns()
  }

  def size(): Int = {
    return fileDataFrame.size()
  }

  def getLabelList(): util.LinkedList[String] = {
    return this.fileDataFrame.getLabelList
  }

  def getContent(): util.LinkedList[util.ArrayList[String]] = {
    return this.fileDataFrame.getContent
  }

  override def filter(label: String, pred: Predicate[String]): Data = {
    this.fileDataFrame.query(label, pred)
  }

  override def count(): Int = 1

  def accept(v: VisitorTrait): Unit = {
    v.visit(this)
  }

}
