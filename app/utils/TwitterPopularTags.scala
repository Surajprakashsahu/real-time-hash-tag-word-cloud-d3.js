package utils

import org.apache.spark.streaming.{ Seconds, StreamingContext }
import StreamingContext._
import org.apache.spark.SparkContext._
import org.apache.spark.SparkContext
import org.apache.spark.streaming.twitter._
import play.api.Play
import org.apache.spark.SparkConf
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer

import java.io._;
import java.io.IOException;
 
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._



object TwitterPopularTags {

  def TwitterStreamUtil {

    val data = new HashMap[String, Int]
    // Twitter Authentication credentials
    val consumerKey = Play.current.configuration.getString("consumer_key").get
    val consumerSecret = Play.current.configuration.getString("consumer_secret").get
    val accessToken = Play.current.configuration.getString("access_token").get
    val accessTokenSecret = Play.current.configuration.getString("access_token_secret").get

    // Authorising with your Twitter Application credentials
    val twitter = new TwitterFactory().getInstance()
    twitter.setOAuthConsumer(consumerKey, consumerSecret)
    twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret))
    
    val driverPort = 8080
    val driverHost = "localhost"
    val conf = new SparkConf(false) // skip loading external settings
      .setMaster("local[*]") // run locally with enough threads
      .setAppName("firstSparkApp")
      .set("spark.logConf", "true")
      .set("spark.driver.port", s"$driverPort")
      .set("spark.driver.host", s"$driverHost")
      .set("spark.akka.logLifecycleEvents", "true")

   // val sc = new SparkContext(conf)
	val ssc = new StreamingContext(conf, Seconds(2))
    val filters = Seq("#")
    val stream = TwitterUtils.createStream(ssc, Option(twitter.getAuthorization()))

	val allData = stream.flatMap(status => status.getText.split(" "))
	//stream.saveAsTextFiles("streaming/streamtxt")
	
    val hashTags = stream.flatMap(status => status.getText.split(" ").filter(_.startsWith("#")))
    
    val topCounts60 = hashTags.map((_, 1)).reduceByKeyAndWindow(_ + _, Seconds(60))
      .map { case (topic, count) => (count, topic) }
      .transform(_.sortByKey(false))
          
    val topCounts10 = hashTags.map((_, 1)).reduceByKeyAndWindow(_ + _, Seconds(10))
      .map { case (topic, count) => (count, topic) }
      .transform(_.sortByKey(false))

    // Print popular hashtags
	 var json = ("name" -> (None: Option[String])) ~ ("value" -> (None: Option[Int]))
	 var jsontxt=new ListBuffer[String]()
	 
	 var finaltxt=""
    topCounts60.foreachRDD(rdd => {
      val topList = rdd.take(91)
      println("\nPopular topics in last 60 seconds (%s total):".format(rdd.count()))
	  //finaltxt=""
	  //jsontxt=""
      topList.foreach { 
	  case (count, tag) => {
				println("%s (%s tweets)".format(tag, count))
				json = ("key" -> tag) ~ ("value" -> count)
				
				jsontxt += compact(render(json))
				//finaltxt = finaltxt +" , "+ jsontxt 
			} 
			
		finaltxt=jsontxt.mkString(", \n")
		finaltxt.reverse
		//finaltxt = compact(render(jsontxt))
		finaltxt = "[  "+finaltxt+"  ]"
		//println("\nFinalTxt = (%s):",finaltxt)
		val pw = new PrintWriter(new File("hello.json" ))
		pw.write(finaltxt)
		pw.close
		//finaltxt = ""
		
	  }
	  jsontxt = new ListBuffer[String]()
	  
		
	})

    topCounts10.foreachRDD(rdd => {
      val topList = rdd.take(5)
      println("\nPopular topics in last 10 seconds (%s total):".format(rdd.count()))
      topList.foreach { case (count, tag) => println("%s (%s tweets)".format(tag, count)) }
    })

/*	
	case class Winner(id: Long, numbers: List[Int])
	case class Lotto(id: Long, winningNumbers: List[Int], winners: List[Winner], drawDate: Option[java.util.Date])

	val winners = List(Winner(23, List(2, 45, 34, 23, 3, 5)), Winner(54, List(52, 3, 12, 11, 18, 22)))
	val lotto = Lotto(5, List(2, 45, 34, 23, 7, 5, 3), winners, None)

	val json =
    ("lotto" ->
      ("lotto-id" -> lotto.id) ~
      ("winning-numbers" -> lotto.winningNumbers) ~
      ("draw-date" -> lotto.drawDate.map(_.toString)) ~
      ("winners" ->
        lotto.winners.map { w =>
          (("winner-id" -> w.id) ~
           ("numbers" -> w.numbers))}))

	println(compact(render(json)))
	val jsontxt = compact(render(json))
	val pw = new PrintWriter(new File("hello.json" ))
	pw.write(jsontxt)
	pw.close
*/
	
    ssc.start()
    ssc.awaitTermination()
  }

}
