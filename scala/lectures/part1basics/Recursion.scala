package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else  {
      println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)

      result
    }
  }

  println(factorial(10))
  //println(factorial(50000))

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt =
      if(x <= 1) accumulator
      else factorialHelper(x-1, x*accumulator)

    factorialHelper(n,1)
  }

  println(anotherFactorial(500))

  @tailrec
  def concateTailRec(aString: String, n: Int, accumulator: String): String =
    if (n <= 0) accumulator
    else concateTailRec(aString, n-1, aString + accumulator)

  println(concateTailRec("hello", 3, ""))


  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)

    isPrimeTailrec(n / 2, true)
  }

  println(isPrime(6))


  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciTailrec(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fibonacciTailrec(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fibonacciTailrec(2, 1, 1)
  }

  println(fibonacci(8))
}
