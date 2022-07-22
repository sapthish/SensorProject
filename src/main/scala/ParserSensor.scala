class ParserSensor {
  def parSensor(s: String) = {
    val fields =  s.split(",")
    val sensor_id = fields(0)
    val values = fields(1).toDouble
    (sensor_id,values)
  }

}