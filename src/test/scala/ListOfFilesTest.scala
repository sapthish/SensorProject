import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

class ListOfFilesTest extends FunSuite {

  test("tet"){
    val a = new ListOfFiles("src/main/resources/").getListOfFiles
    assert(a != null)
  }



}