package advancedscala

import recursion.RecursionTest.{isGreaterThanFour, roundDoubleValue}
import composite.{DirectoryScala, FileScala}
import dataframe.ScalaDataFrame

trait Talkable {
  def talk(): String = ""
}

trait XAnimal extends Talkable {
  override def talk(): String = "I am an Animal"
}

trait XPerson {
  def say(): String = "I am a person"
}

object AdvancedScala extends scala.App {

  // ----------------- Runtime multiple inheritance -----------------

  val dataframeTalkable = new FileScala("src/resources/recursion.csv") with XPerson with XAnimal
  println(dataframeTalkable.say())
  println(dataframeTalkable.talk())
  println(dataframeTalkable.size())
  println()

  // ------------------------- for loops -------------------------

  val dir = new DirectoryScala("src/resources/dir1")
  val dir2 = new DirectoryScala("src/resources/dir2")

  val csv = new FileScala("src/resources/dir1/ages.csv")
  val json = new FileScala("src/resources/dir2/cities.json")
  val txt = new FileScala("src/resources/dir1/example.txt")

  val list = List(dir,dir2,csv,json,txt)

  println("List of Dataframes that are in dir1:")
  println(for (dataframe <- list; name = dataframe.getName; if name.contains("dir1")) yield name)
  println()

  // ------------------------ fold, tabulate ------------------------

  val sizeList = for (dataframe <- list) yield dataframe.size()
  val totalSize = sizeList.foldLeft(0) (_ + _)
  println("sizeList: " + sizeList)
  println("sizeList.foldLeft(0) (_ + _) a.k.a. total size: " + totalSize)

  val generateList = List.tabulate(11) (n => n * totalSize)
  println("list of (totalSize * [0, 10]): " + generateList)
  println()

  // --------------------- Partial parametrization ---------------------

  println("--- Round Double to Int ---")
  val df: ScalaDataFrame = new FileScala("src/resources/recursion.csv")
  val applyToGreaterThanFour = df.listFilterMapStack(isGreaterThanFour, roundDoubleValue, _: List[String])
  println("applyToGreaterThanFour(Value): " + applyToGreaterThanFour(df.getColumn("Value")))

}
