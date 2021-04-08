package Project

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StructField, StructType}
import org.apache.spark.sql.types._

object ScalaProject {

  def main(args: Array[String]): Unit ={
    val spark = SparkSession
      .builder
      .appName("ScalaProject")
      .master("local").config("spark.driver.host","localhost")
      .getOrCreate()

    val segmentSchema = StructType(
      StructField("phoneNo", IntegerType):: Nil)




    val ruleschema = StructType(
      StructField("appID", IntegerType) ::
        StructField("appName", StringType) ::
        StructField("startHour", IntegerType) ::
        StructField("endHour", IntegerType) ::
        StructField("vloumnOfUsage", IntegerType) ::  Nil)

    val dataschema = StructType(
      StructField("phoneNoD", IntegerType) ::
        StructField("appIDD", IntegerType) ::
        StructField("vloumnOfUsageD", IntegerType) ::
        StructField("timeStamp", StringType) :: Nil)

    val segmentFile = spark.read
      .format("csv")
      .option("header" , "false")
      .schema(segmentSchema)
      .load("SegmentRules/SEGMENT.csv")
    //segmentFile.createOrReplaceTempView("segmentData")

    val rulesFile = spark.read
      .format("csv")
      .option("header" , "false")
      .schema(ruleschema)
      .load("SegmentRules/RULES.csv")
    //rulesFile.createOrReplaceTempView("rulesData")


    segmentFile.show()
    rulesFile.show()



    val accessLines = spark.readStream.schema(dataschema).csv("data/")

    //val selectPhone = accessLines.select("PhoneNo").head(5)

    /*val query = (accessLines
      .writeStream.outputMode("append")
      .format("console")
      .queryName("Phone")
      .start())*/

    val segmentCheck = accessLines.join(segmentFile , accessLines("phoneNoD") === segmentFile("phoneNo") , "inner")
      .filter("appIDD is not Null")

    val ruleCheck = segmentCheck.join(rulesFile ,segmentCheck("appIDD") === rulesFile("appID"))
      .filter("vloumnOfUsageD >= vloumnOfUsage")
      .select("phoneNo", "appID" , "appName" , "startHour" , "endHour" , "vloumnOfUsageD" , "timeStamp")


    val query = (ruleCheck
      .writeStream
      .format("csv")
      .option("format", "append")
      .option("path" , "OutputStream/")
      .option("checkpointLocation","CheckPoint/")
      .partitionBy("appName")
      .outputMode("append")
      .start())


    query.awaitTermination()
    spark.stop()
  }

}
