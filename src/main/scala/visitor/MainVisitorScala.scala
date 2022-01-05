package visitor

import composite.{DirectoryScala, FileScala}
import factory.DataFrame

import java.util
import scala.jdk.CollectionConverters._

object MainVisitorScala extends scala.App {

  val f1 = new FileScala("src/main/resources/dir1/DimenLookupAge8277.csv")
  val f2 = new FileScala("src/main/resources/dir2/subdir2/cities.json")
  val f3 = new FileScala("src/main/resources/dir1/example.txt")

  val dir1 = new DirectoryScala("src/main/resources/dir1")
  val dir2 = new DirectoryScala("src/main/resources/dir2")

  val list = util.Arrays.asList(dir1, f1, f3)
  val list2 = util.Arrays.asList(dir2, f2)

  var v: VisitorTrait = null

  System.out.println("list1 FilterVisitor(SortOrder, x => x > 113)")

  for (d <- list.asScala) {
    v = new FilterVisitor("SortOrder", x => Integer.parseInt(x) > 113);
    d.accept(v)
    println(v.getResult())
  }

  System.out.println("list1 CounterVisitor()")

  for (d <- list2.asScala) {
    v = new CounterVisitor()
    d.accept(v)
    println("directoryCount: " + v.asInstanceOf[CounterVisitor].directoryCount)
    println("fileCount: " + v.asInstanceOf[CounterVisitor].fileCount)
    println("getResult: " + v.getResult)
    println()
  }

}
