package composite

import dataframe.ScalaDataFrame
import factory.Data
import visitor.VisitorScala

import java.util
import java.util.function.Predicate
import scala.jdk.CollectionConverters._

class FileScala(filePath: String) extends ScalaDataFrame {

  val fileDataFrame = new FileData(filePath)
  val name: String = filePath

  def getData: Data = fileDataFrame.getData

  def getName: String = name

  def at(id: Int, label: String): String = fileDataFrame.at(id, label)

  def columns(): Int = fileDataFrame.columns()

  def size(): Int = fileDataFrame.size()

  def getLabelList: util.LinkedList[String] = fileDataFrame.getLabelList

  def getColumn(label: String): List[String] = fileDataFrame.getColumn(label).asScala.toList

  def filter(label: String, predicate: Predicate[String]): Data = fileDataFrame.query(label, predicate)

  def fileCount(): Int = 1

  def directoryCount(): Int = 0

  def accept(v: VisitorScala): Unit = v.visit(this)

  override def listFilterMapStack[A, B](predicate: A => Boolean, method: A => B, list: List[A]): List[B] = list match {
    case Nil => Nil
    case front::rest => if (predicate(front))
      method(front) :: listFilterMapStack(predicate, method, rest)
    else
      listFilterMapStack(predicate, method, rest)
  }

  override def listFilterMapTail[A, B](predicate: A => Boolean, method: A => B, list: List[A], accumulator: List[B]): List[B] = list match {
    case Nil => accumulator
    case front::rest => if (predicate(front))
      listFilterMapTail(predicate, method, rest, accumulator :+ method(front))
    else
      listFilterMapTail(predicate, method, rest, accumulator)
  }

}
