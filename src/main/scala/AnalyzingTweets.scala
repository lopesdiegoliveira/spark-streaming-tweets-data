package com.sparkstreaming.tweetsdata

import com.sparkstreaming.tweetsdata.Utilities._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._

/** Simple application to listen to a stream of Tweets and print them out */
object AnalyzingTweets {

  /** Our main funct
   * ion where the action happens */
  def main(args: Array[String]) {

    // Configure Twitter credentials using twitter.txt
    setupTwitter()

    // Set up a Spark streaming context named "PrintTweets" that runs locally using
    // all CPU cores and one-second batches of data
    val ssc = new StreamingContext("local[*]", "AnalyzingTweets", Seconds(1))

    // Get rid of log spam (should be called after the context is set up)
    setupLogging()

    // Create a DStream from Twitter using our streaming context
    val keywords = Array("COVID-19")
    val tweets = TwitterUtils.createStream(ssc, None, keywords)

    // Now extract the text of each status update into RDD's using map()
    val statuses = tweets.map(status => status.getText())

    // Print out the first ten
    statuses.print()

    // Kick it all off
    ssc.start()
    ssc.awaitTermination()
  }
}