import java.io.File
import scala.Double.NaN
import scala.io.Source


object sensorDetails extends App {

  /***
   * Kindly provide the path of the directory where the .csv files are place
   */

  val directory = "C:\\Users\\User.DESKTOP-0RUQ7FF\\Desktop\\Big Data\\sensor\\"

  val listOfFiles = new ListOfFiles(directory).getListOfFiles
  val listOfSensors = for (fileName <-listOfFiles) yield  {Source.fromFile(fileName).getLines.drop(1).toList}
  val listOfSensorsFlatten = listOfSensors.flatMap(x => x)
  /***
   * Convert the all files in list(sersorid, Values)
   */
  val ParSensor= new ParserSensor
  val sensorDetl = listOfSensorsFlatten.map(x => ParSensor.parSensor(x))
  val sensorDetlToMap = sensorDetl.toMap
  val sensorWithoutNaN = sensorDetl.filterNot(x => x._2.isNaN)
  val sensorWithNaN = sensorDetl.filter(x => x._2.isNaN).size

  /***
   * Group the values/Humidity base on sensor id
   */

  val sensorGroupByID = sensorWithoutNaN.groupBy(_._1)
  /***
   * perform operation to calculate min, Avg and Max humidity for each sensor id
   */

  val senAvg = sensorGroupByID.transform((x,y) => (y.map(_._2).sum/ y.map(_._2).size))
  val senMin = sensorGroupByID.transform((x,y) => y.map(_._2).min)
  val senMax = sensorGroupByID.transform((x,y) => y.map(_._2).max)
  val senGrpKey = sensorDetlToMap.keySet
  /***
   * mapping the values to each sensor id along with NaN info
   */


  val senValues = senGrpKey.map(x =>
    (x,senMin.getOrElse((x),NaN),senAvg.getOrElse((x),NaN),senMax.getOrElse((x),NaN))).toList.sortBy(-_._3)

  /***
   * Output of the Program
   */

  println("Num of processed files: " + listOfFiles.size)
  println("Num of processed measurements: " + sensorDetl.size)
  println("Num of failed measurements: " + sensorWithNaN)
  println("Sensors with highest avg humidity: " +
    "sensor-id,min,avg,max " +
    senValues)




}