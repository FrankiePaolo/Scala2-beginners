package lectures.part1basics

object Functions extends App {
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aRepetedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepetedFunction(aString, n-1)
  }

  println(aRepetedFunction("hello", 3))

  def greetingFunction(name: String, age: Int): String = {
    "Hi, my name is " + name + " and I am " + age + " years old"
  }

  println(greetingFunction("John", 30))

  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else n * factorial(n-1)
  }

  println(factorial(5))

  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  println(fibonacci(8))

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)
    }
    isPrimeUntil(n / 2)
  }

  println(isPrime(37))
}
