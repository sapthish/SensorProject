import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

class ListOfFilesTest extends FunSuite {

test("test") {

  val l = new ListOfFiles

  assert(l.getListOfFiles("src/main/resources/") != null)
}


}
