package lectures.part3fp

import scala.util.Random

object Sequences extends App {
  // Seq
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7,5,6))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10

  (1 to 3).foreach(_ -> println("Hola"))

  // Lists
  val aList = List(1,2,3)
  val prepended = 32 +: aList :+ 43
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  //  Arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // Arrays and Seq
  val numberSeq: Seq[Int] = numbers // implicit conversion

  // Vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // Vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 100000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      _ <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))

}

