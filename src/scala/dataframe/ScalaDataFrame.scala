package dataframe

import factory.Data
import visitor.VisitorScala

import java.util.function.Predicate

trait ScalaDataFrame {

  def getName: String

  def at(id: Int, label: String): String

  def columns(): Int

  def size(): Int

  def getLabelList: java.util.LinkedList[String]

  def getColumn(label: String): List[String]

  def accept(visitor: VisitorScala): Unit

  def listFilterMapStack[A, B](predicate: A => Boolean, method: A => B, list: List[A]): List[B]

  def listFilterMapTail[A, B](predicate: A => Boolean, method: A => B, list: List[A], accumulate: List[B]): List[B]

}
