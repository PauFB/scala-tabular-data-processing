package composite

object CompositeScalaTest extends scala.App {

  var csv = new FileScala("src/main/resources/ages.csv")
  println("--- ages.csv ---")
  println("columns(): " + csv.columns())
  println("size(): " + csv.size())
  println("at(1, Code): " + csv.at(1, "Code"))
  println("at(1000, Code): " + csv.at(1000, "Code"))
  println("at(1, NoLabel): " + csv.at(1, "NoLabel"))
  println()

  var dir = new DirectoryScala("src/main/resources/dir1")
  println("--- dir1 ---")
  println("columns(): " + dir.columns())
  println("size(): " + dir.size())
  println("at(149, Code): " + dir.at(149, "Code"))
  println("at(1000, Code): " + dir.at(1000, "Code"))
  println("at(1, NoLabel): " + dir.at(1, "NoLabel"))

}
