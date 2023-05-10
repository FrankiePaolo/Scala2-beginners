package lectures.part3fp

object AnonymousFunctions extends App{

  val doubler = (x: Int) => x * 2
  val adder : (Int, Int) => Int = (a: Int, b: Int) => a + b
  val justDoSomething = () => 3

  println(justDoSomething)
  println(justDoSomething())

  //ugly but seldom used
  val stringToInt = { (str: String) =>
    str.toInt
  }

  val niceIncrementer: Int => Int = _ + 1
  println(niceIncrementer(1))
  val niceAdder: (Int,Int)=>Int =  _ + _
  println(niceAdder(1,2))
}
