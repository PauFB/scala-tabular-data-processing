package composite

import dataframe.DataFrameTrait

object MainScala extends scala.App {

  var dir = new DirectoryScala("src/main/resources/dir1")
  println("--- dir1 ---")
  println("columns(): " + dir.columns())
  println("size(): " + dir.size())
  println("dir at(149, Code): " + dir.at(149, "Code"));
  println()

  def isGreaterThanFour(value: String): Boolean =
    if (value.toDouble > 4) true
    else false

  def roundDoubleValue(value: String): Int = {
    Math.round(value.toDouble).toInt
  }

  val df: DataFrameTrait = new FileScala("src/main/resources/example.csv")
  println("--- Round Double to Int ---")
  println("stack DataFrameTrait: " + df.listFilterMapStack(isGreaterThanFour, roundDoubleValue, df.getColumn("Value")))
  println("tail DataFrameTrait: " + df.listFilterMapTail(isGreaterThanFour, roundDoubleValue, df.getColumn("Value"), Nil))
  println()


  def StringContainsHello(value: String): Boolean = {
    if (value.contains("Hello")) true
    else false
  }

  def replaceStringPattern(value: String): String = {
    value.replace("Hello", "Goodbye")
  }

  println("--- Replace String ---")
  println("stack DataFrameTrait: " + df.listFilterMapStack(StringContainsHello, replaceStringPattern, df.getColumn("Sentence")))
  println("tail DataFrameTrait: " + df.listFilterMapTail(StringContainsHello, replaceStringPattern, df.getColumn("Sentence"), Nil))

}
