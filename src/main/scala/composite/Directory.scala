package composite

import dataframe.DataFrameTrait

class Directory(directoryPath: String) extends DataFrameTrait {

  private val children = List[DataFrameTrait]

  private val directory = new java.io.File(directoryPath)

  for (file <- directory.listFiles()) {
      if (!file.isDirectory) {
        this.children = this.children ::: new File(file.getAbsolutePath)
      } else {
        this.children = this.children ::: new Directory(file.getAbsolutePath)
      }
  }

  def columns(): Int = {
    return null
  }

  def size(): Int = {
    return null
  }

}
