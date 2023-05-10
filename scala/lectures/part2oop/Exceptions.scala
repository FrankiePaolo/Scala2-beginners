package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  //println(x.length)
  // this ^^ will crash with a NPE

  // 1. throwing and catching exceptions
  //val aWeirdValue: String = throw new NullPointerException

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    getInt(true)
  }catch {
    case _: RuntimeException => 43
  }finally {
   println("finally")
  }
  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception
/*  val exception = new MyException
  throw exception*/


  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash with SOError
    3. PocketCalculator
      - add(x,y)
      - subtract(x,y)
      - multiply(x,y)
      - divide(x,y)
   */

  // 1.
  //val array = Array.ofDim(Int.MaxValue)

  // 2.
  //def infinite: Int = 1 + infinite
  //val noLimit = infinite

  // 3.
  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }


    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  //println(PocketCalculator.divide(2,0))

}
