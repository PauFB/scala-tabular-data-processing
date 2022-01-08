package visitor

import composite.{DirectoryScala, FileScala}
import dataframe.ScalaDataFrame

import java.util
import scala.jdk.CollectionConverters._

object VisitorScalaTest extends scala.App {

  val f1 = new FileScala("src/main/resources/ages.csv")
  val f2 = new FileScala("src/main/resources/cities.json")
  val f3 = new FileScala("src/main/resources/example.txt")

  val dir1 = new DirectoryScala("src/main/resources/dir1")
  val dir2 = new DirectoryScala("src/main/resources/dir2")

  val list: util.List[ScalaDataFrame] = util.Arrays.asList(dir1, f1, f3)
  val list2: util.List[ScalaDataFrame] = util.Arrays.asList(dir2, f2)

  var v: VisitorScala = null

  System.out.println("list1 FilterVisitor(SortOrder > 140)")
  for (d <- list.asScala) {
    v = new FilterVisitor("SortOrder", x => Integer.parseInt(x) > 140)
    d.accept(v)
    println(v.getResult)
  }
  println()

  System.out.println("list2 CounterVisitor()")
  for (d <- list2.asScala) {
    v = new CounterVisitor()
    d.accept(v)
    println(d.getName + " DataFrame files = " + v.asInstanceOf[CounterVisitor].fileCount + " and DataFrame dirs = " + v.asInstanceOf[CounterVisitor].directoryCount)
    println(d.getName + " Dataframe archives = " + v.getResult)
    println()
  }

}
