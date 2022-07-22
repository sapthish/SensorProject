import java.io.File

class ListOfFiles(dir: String) extends GetFiles {

  def getListOfFiles:List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }


}
