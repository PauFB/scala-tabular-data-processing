package composite

object MainScala extends scala.App {

  var dir = new DirectoryScala("src/main/resources/dir1")
  println("--- dir1 ---")
  println("columns(): " + dir.columns())
  println("size(): " + dir.size())
  println("dir at(149, Code): " + dir.at(149, "Code"));

}
