package test

import composite.{DirectoryScala, FileScala}
import factory.Data
import dataframe.ScalaDataFrame
import visitor.{CounterVisitor, FilterVisitor, VisitorScala}

import java.util
import scala.jdk.CollectionConverters.*

object VisitorScalaTest extends scala.App {

  val f1 = new FileScala("src/resources/ages.csv")
  val f2 = new FileScala("src/resources/cities.json")
  val f3 = new FileScala("src/resources/example.txt")

  val dir1 = new DirectoryScala("src/resources/dir1")
  val dir2 = new DirectoryScala("src/resources/dir2")

  val list: util.List[ScalaDataFrame] = util.Arrays.asList(dir1, f1, f3)
  val list2: util.List[ScalaDataFrame] = util.Arrays.asList(dir2, f2)

  var v: VisitorScala = null

  System.out.println("list1 FilterVisitor(SortOrder > 140)")
  v = new FilterVisitor("SortOrder", x => Integer.parseInt(x) > 140)
  for (d <- list.asScala) {
    v.asInstanceOf[FilterVisitor].setResult(new Data(new util.LinkedList[String], new util.LinkedList[util.ArrayList[String]]()))
    d.accept(v)
    v.asInstanceOf[FilterVisitor].getResult
    println(v.asInstanceOf[FilterVisitor].getResult)
  }
  println()

  System.out.println("list2 CounterVisitor()")
  v = new CounterVisitor()
  for (d <- list2.asScala) {
    v.asInstanceOf[CounterVisitor].setResult(0,0)
    d.accept(v)
    println(d.getName + " DataFrame files = " + v.asInstanceOf[CounterVisitor].fileCount + " and DataFrame dirs = " + v.asInstanceOf[CounterVisitor].directoryCount)
    println()
  }

}
