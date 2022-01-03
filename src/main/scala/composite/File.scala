package composite

import dataframe.DataFrameTrait

class File(filePath: String) extends DataFrameTrait {

  val fileDataFrame = new FileCOMP(filePath)

  def columns(): Int = {
    return fileDataFrame.columns()
  }

  def size(): Int = {
    return fileDataFrame.size()
  }

}
