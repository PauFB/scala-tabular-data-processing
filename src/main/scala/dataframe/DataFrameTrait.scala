package dataframe

trait DataFrameTrait {

  def at(id: Int, label: String): String

  def accept(visitor: VisitorTrait): Unit

  def columns(): Int

  def size(): Int

  def getLabelList(): java.util.LinkedList[String]

  def getContent(): java.util.LinkedList[java.util.ArrayList[String]]

}
