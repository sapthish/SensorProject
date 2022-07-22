import org.scalatest.FunSuite

class ParserSensorTest extends FunSuite {

  test("ParserSensor"){

    val parser = new ParserSensor
    val sensorDetl = parser.parSensor("s1,10")

    assertResult(("s1",10.00)){sensorDetl}
  }

}