package advancedscala

import composite.MainScala.{isGreaterThanFour, roundDoubleValue}
import composite.{DirectoryScala, FileScala}
import dataframe.DataFrameTrait

// ----------------- Runtime multiple inheritance -----------------

trait Talkable {
  def talk(): String {}
}

trait XAnimal extends Talkable {
  override def talk(): String = "I am an Animal"
}

trait XPerson {
  def say(): String = "I am a person"
}

object MainAdvancedScala extends scala.App {

  // ----------------- Runtime multiple inheritance -----------------

  val et = new FileScala("src/main/resources/example.csv") with XPerson with XAnimal
  println(et.say())
  println(et.talk())
  println(et.size())
  println()

  // ------------------------- for - yield -------------------------

  val dir = new DirectoryScala("src/main/resources/dir1")
  val dir2 = new DirectoryScala("src/main/resources/dir2")

  val csv = new FileScala("src/main/resources/dir1/DimenLookupAge8277.csv")
  val json = new FileScala("src/main/resources/dir2/cities.json")
  val txt = new FileScala("src/main/resources/dir1/example.txt")
  val llista = List(dir,dir2,csv,json,txt)
  println(for (dataframe <- llista; name = dataframe.getName(); if (name.contains("dir1"))) yield name)
  println()

  // ------------------------ fold, tabulate ------------------------

  val sizeList = for (dataframe <- llista) yield dataframe.size()
  val totalSize = sizeList.foldLeft(0) (_ + _)
  println("sizeList: " + sizeList)
  println("sizeList.foldleft(0) (_ + _) a.k.a. total size: " + totalSize)

  val generateList = List.tabulate(11) (n => n * totalSize)
  println("list of (totalSize * [0, 10]): " + generateList)
  println()

  // --------------------- Partial parametrization ---------------------

  println("--- Round Double to Int ---")
  val df: DataFrameTrait = new FileScala("src/main/resources/example.csv")
  val applyToGreaterThanFour = df.listFilterMapStack(isGreaterThanFour, roundDoubleValue, _: List[String])
  println("tail DataFrameTrait (curry): " + applyToGreaterThanFour(df.getColumn("Value")))
  println()

}
