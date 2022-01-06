package composite

import dataframe.DataFrameTrait
import factory.Data
import visitor.VisitorTrait

import java.util
import java.util.function.Predicate

import scala.jdk.CollectionConverters._

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

  override def fileCount(): Int = 1

  override def directoryCount(): Int = 0

  def accept(v: VisitorTrait): Unit = {
    v.visit(this)
  }

  def getColumn(label: String): List[String] = {
    return fileDataFrame.getColumn(label).asScala.toList
  }

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

  override def getName(): String = {
    return this.name
  }

}
