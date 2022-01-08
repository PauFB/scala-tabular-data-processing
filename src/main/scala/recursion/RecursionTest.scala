package recursion

import composite.FileScala
import dataframe.ScalaDataFrame

object RecursionTest extends scala.App {

  val df: ScalaDataFrame = new FileScala("src/main/resources/recursion.csv")

  def isGreaterThanFour(value: String): Boolean =
    if (value.toDouble > 4) true
    else false

  def roundDoubleValue(value: String): Int = Math.round(value.toDouble).toInt

  println("--- Round Double to Int ---")
  println("stack DataFrameTrait: " + df.listFilterMapStack(isGreaterThanFour, roundDoubleValue, df.getColumn("Value")))
  println("tail DataFrameTrait: " + df.listFilterMapTail(isGreaterThanFour, roundDoubleValue, df.getColumn("Value"), Nil))
  println()


  def StringContainsHello(value: String): Boolean =
    if (value.contains("Hello")) true
    else false

  def replaceStringPattern(value: String): String = value.replace("Hello", "Goodbye")

  println("--- Replace String ---")
  println("stack DataFrameTrait: " + df.listFilterMapStack(StringContainsHello, replaceStringPattern, df.getColumn("Sentence")))
  println("tail DataFrameTrait: " + df.listFilterMapTail(StringContainsHello, replaceStringPattern, df.getColumn("Sentence"), Nil))


}
