package composite

import dataframe.ScalaDataFrame
import factory.Data
import visitor.VisitorScala

import java.io.File
import java.util
import java.util.function.Predicate
import scala.jdk.CollectionConverters.*

class DirectoryScala() extends ScalaDataFrame {

  var name: String = ""
  var children: List[ScalaDataFrame] = List[ScalaDataFrame]()

  def this(directoryPath: String) = {
    this()
    name = directoryPath
    children = List[ScalaDataFrame]()

    var f: FileScala = null
    var d: DirectoryScala = null
    val directory = new File(directoryPath)
    val archives = directory.listFiles
    if (archives != null)
      for (file <- archives) { // For every archive that contains a DataFrame in directoryPath, add it to children
        if (!file.isDirectory) {
          f = new FileScala(file.getAbsolutePath)
          if (f.getData != null) children = children :+ f
        }
        else {
          d = new DirectoryScala(file.getAbsolutePath)
          if (d.getChildren.nonEmpty) children = children :+ d
        }
      }
    else System.out.println("Directory is empty")
  }

  def getChildren: List[ScalaDataFrame] = this.children

  def getName: String = this.name

  def at(id: Int, label: String): String = {
    var aux = id
    for (child <- children) {
      if (aux > child.size() - 1)
        aux = aux - child.size()
      else return child.at(aux, label)
    }
    null
  }

  def columns(): Int = getLabelList.size

  def size(): Int = {
    var result = 0
    for (child <- children) {
      result = result + child.size()
    }
    result
  }

  def getLabelList: util.LinkedList[String] = {
    val labelList = new util.LinkedList[String]()
    var newLabelList = new java.util.LinkedList[String]()
    for (child <- children) { //For every child
      newLabelList = child.getLabelList
      for (s <- newLabelList.asScala) {
        if (!labelList.contains(s))
          labelList.add(s) // Add labels that are not in labelList
      }
    }
    labelList
  }

  def getColumn(label: String): List[String] = {
    val column: List[String] = null
    for (child <- children) { // For every child
      if (child.getColumn(label) != null) column :+ child.getColumn(label) //Accumulate the elements of the column indexed by label
    }
    column
  }

  def accept(v: VisitorScala): Unit = v.visit(this)

  override def listFilterMapStack[A, B](predicate: A => Boolean, method: A => B, list: List[A]): List[B] = list match {
    case Nil => Nil
    case front :: rest => if (predicate(front)) //If the front fulfil the condition
      method(front) :: listFilterMapStack(predicate, method, rest) //return the result of the applied operation to it added to the recursive call of the rest
    else
      listFilterMapStack(predicate, method, rest) //If not return the recursive call of the rest
  }

  override def listFilterMapTail[A, B](predicate: A => Boolean, method: A => B, list: List[A], accumulator: List[B]): List[B] = list match {
    case Nil => accumulator
    case front :: rest => if (predicate(front)) //If the front fulfil the condition
      listFilterMapTail(predicate, method, rest, accumulator :+ method(front)) //return the recursive call of the rest adding the result of the applied operation to it
    else
      listFilterMapTail(predicate, method, rest, accumulator) //If not return the recursive call of the rest
  }

}
