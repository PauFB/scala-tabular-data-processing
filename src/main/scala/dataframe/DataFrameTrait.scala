package dataframe

import factory.Data
import visitor.VisitorTrait

import java.util
import java.util.function.Predicate

trait DataFrameTrait {

  def at(id: Int, label: String): String

  def accept(visitor: VisitorTrait): Unit

  def columns(): Int

  def size(): Int

  def filter(label: String, pred: Predicate[String]): Data

  def getLabelList(): java.util.LinkedList[String]

  def getContent(): java.util.LinkedList[java.util.ArrayList[String]]

  def getName(): String

  def getColumn(label: String): List[String]

  def listFilterMapStack[A, B](predicate: A => Boolean, method: A => B, list: List[A]): List[B]

  def listFilterMapTail[A, B](predicate: A => Boolean, method: A => B, list: List[A], accumulate: List[B]): List[B]

  def fileCount(): Int

  def directoryCount(): Int

}
