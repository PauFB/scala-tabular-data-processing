package visitor

import dataframe.DataFrameTrait
import factory.Data

import java.util.function.Predicate

trait VisitorTrait {

  def visit(dataFrame: DataFrameTrait): Unit
  //def visit[T](f: T => Boolean): Unit

  def getResult[T](): T

}
