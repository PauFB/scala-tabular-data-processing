package composite

import dataframe.DataFrameTrait
import factory.{Data, DataFrame}
import visitor.VisitorTrait

import java.io.File
import java.util
import java.util.function.Predicate
import java.util.{ArrayList, LinkedList}
import scala.jdk.CollectionConverters._

class DirectoryScala() extends DataFrameTrait {

  var name: String = null
  var children: java.util.List[DataFrameTrait] = new java.util.LinkedList[DataFrameTrait]()

  def this(directoryPath: String) {
    this()

    this.name = directoryPath
    children = new LinkedList[DataFrameTrait]
    try {
      val directory = new File(directoryPath)
      for (file <- directory.listFiles) {
        if (!file.isDirectory) {
          children.add(new FileScala(file.getAbsolutePath))
        } else {
          children.add(new DirectoryScala(file.getAbsolutePath))
        }
      }
    } catch {
      case e: Exception =>
        System.out.println("Directory " + this.name + " is empty.")
    }

  }

  /*
  private val children = new java.util.LinkedList[DataFrameTrait]()

  private val directory = new java.io.File(directoryPath)

  for (file <- directory.listFiles()) {
      if (!file.isDirectory) {
        this.children.add(new FileScala(file.getAbsolutePath))
      } else {
        this.children.add(new DirectoryScala(file.getAbsolutePath))
      }
  }
  */

  def at(id: Int, label: String): String = {
    val labelList = this.getLabelList
    val content = new util.LinkedList[util.ArrayList[String]]
    var data: Data = null
    try {
      for (i <- 0 until this.getLabelList.size) {
        content.add(new util.ArrayList[String])
      }
      for (child <- this.children.asScala) {
        for (i <- 0 until labelList.size) {
          content.get(i).addAll(child.getContent.get(i))
        }
      }
    }
    catch {
      case e: Exception =>
        return null
    }
    data = new Data(labelList, content)
    return data.at(id, label)
  }

  def getLabelList(): java.util.LinkedList[String] = {
    val labelList: util.LinkedList[String] = new util.LinkedList[String]
    var newlabelList: util.LinkedList[String] = new util.LinkedList[String]
    for (child <- this.children.asScala) {
      newlabelList = child.getLabelList
      for (s <- newlabelList.asScala) {
        if (!labelList.contains(s)) labelList.add(s)
      }
    }
    return labelList
  }

  def getContent(): util.LinkedList[java.util.ArrayList[String]] = {
    val aux = new util.LinkedList[java.util.ArrayList[String]]
    for (child <- this.children.asScala) {
      aux.addAll(child.getContent)
    }
    return aux
  }

  def columns(): Int = {
    return this.getLabelList().size()
  }

  def size(): Int = {
    var result = 0
    for (child <- this.children.asScala) {
      result += child.size
    }
    return result
  }

  override def filter(label: String, predicate: Predicate[String]): Data = {
    var result: Data = null
    var firstHasBeenAdded = false
    for (child <- this.children.asScala) {
      if ((!firstHasBeenAdded) && (child.filter(label, predicate)) != null) {
        result = child.filter(label, predicate)
        firstHasBeenAdded = true
      }
      else if (child.filter(label, predicate) != null) for (i <- 0 until result.getContent.size) {
        result.getContent.get(i).addAll(child.filter(label, predicate).getContent.get(i))
      }
    }
    return result
  }

  def fileCount(): Int = {
    var result = 0
    for (child <- this.children.asScala) {
      result += 1
    }
    return result
  }

  def directoryCount(): Int = {
    var result = 0
    for (child <- this.children.asScala)
      if (child.isInstanceOf[DirectoryScala]) {
        result += 1
        result += child.directoryCount()
      }
    return result
  }

  def accept(v: VisitorTrait): Unit = {
    v.visit(this)
  }

}