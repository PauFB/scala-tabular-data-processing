package composite

import dataframe.ScalaDataFrame
import factory.Data
import visitor.VisitorScala

import java.io.File
import java.util
import java.util.function.Predicate
import scala.jdk.CollectionConverters._

class DirectoryScala() extends ScalaDataFrame {

  var name: String = ""
  var children: util.List[ScalaDataFrame] = new util.LinkedList[ScalaDataFrame]()

  def this(directoryPath: String) = {
    this()
    name = directoryPath
    children = new util.LinkedList[ScalaDataFrame]

    var f: FileScala = null
    var d: DirectoryScala = null
    val directory = new File(directoryPath)
    val archives = directory.listFiles
    if (archives != null) for (file <- archives) { //For every archive that contains a DataFrame in directoryPath, add it to children
      if (!file.isDirectory) {
        f = new FileScala(file.getAbsolutePath)
        if (f.getData != null) children.add(f)
      }
      else {
        d = new DirectoryScala(file.getAbsolutePath)
        if (!d.getChildren.isEmpty) children.add(d)
      }
    }
    else System.out.println("Directory is empty")
  }

  def getChildren: util.List[ScalaDataFrame] = children

  def getName: String = name

  def at(id: Int, label: String): String = {
    var aux = id
    for (child <- children.asScala) {
      if (aux > child.size() - 1)
        aux = aux - child.size()
      else return child.at(aux, label)
    }
    null
  }

  def columns(): Int = getLabelList.size()

  def size(): Int = {
    var result = 0
    for (child <- children.asScala) {
      result = result + child.size()
    }
    result
  }

  def getLabelList: java.util.LinkedList[String] = {
    val labelList = new util.LinkedList[String]
    var newLabelList = new util.LinkedList[String]
    for (child <- children.asScala) {         //For every child
      newLabelList = child.getLabelList
      for (s <- newLabelList.asScala) {
        if (!labelList.contains(s)) labelList.add(s)    //Add labels that are not in labelList
      }
    }
    labelList
  }

  def getColumn(label: String): List[String] = {
    val column: List[String] = null
    for (child <- children.asScala) {         //For every child
      if (child.getColumn(label) != null) column :+ child.getColumn(label) //Accumulate the elements of the column indexed by label
    }
    column
  }

  def filter(label: String, predicate: Predicate[String]): Data = {
    var result: Data = null
    var firstHasBeenAdded = false
    for (child <- children.asScala) {      //For every child
      if ((!firstHasBeenAdded) && child.filter(label, predicate) != null) {
        result = child.filter(label, predicate)     //result takes the first filter
        firstHasBeenAdded = true
      }
      else if (child.filter(label, predicate) != null) for (i <- 0 until result.getContent.size) {
        result.getContent.get(i).addAll(child.filter(label, predicate).getContent.get(i)) //Add to the content of result the content of the rest of filters
      }
    }
    result
  }

  def fileCount(): Int = {
    var result = 0
    for (child <- children.asScala) {
      if (child.isInstanceOf[FileScala]){   //If child is a File add 1
        result +=1
      } else {
        result += child.fileCount()         //If child is a Directory add its number of files
      }
    }
    result
  }

  def directoryCount(): Int = {
    var result = 0
    for (child <- children.asScala)
      if (child.isInstanceOf[DirectoryScala]) {   //If child is a Directory add 1 and its number of directories
        result += 1
        result += child.directoryCount()
      }
    result
  }

  def accept(v: VisitorScala): Unit = v.visit(this)

  override def listFilterMapStack[A, B](predicate: A => Boolean, method: A => B, list: List[A]): List[B] = list match {
    case Nil => Nil
    case front::rest => if (predicate(front))     //If the front fulfil the condition
      method(front) :: listFilterMapStack(predicate, method, rest)  //return the result of the applied operation to it added to the recursive call of the rest
    else
      listFilterMapStack(predicate, method, rest)   //If not return the recursive call of the rest
  }

  override def listFilterMapTail[A, B](predicate: A => Boolean, method: A => B, list: List[A], accumulator: List[B]): List[B] = list match {
    case Nil => accumulator
    case front::rest => if (predicate(front))     //If the front fulfil the condition
      listFilterMapTail(predicate, method, rest, accumulator :+ method(front))  //return the recursive call of the rest adding the result of the applied operation to it
    else
      listFilterMapTail(predicate, method, rest, accumulator)   //If not return the recursive call of the rest
  }

}
