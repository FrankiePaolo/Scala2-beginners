package excercises

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def map[B](transformer: A=>B): MyList[B]
  def filter(predicate: A=>Boolean): MyList[A]
  def flatMap[B](transformer: A=>MyList[B]): MyList[B]
  def forEach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B,C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
  def ++[B >: A](list: MyList[B]): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] =  Cons(element, Empty)
  def printElements: String = ""
  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  def forEach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  def zipWith[B,C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if(!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] =  Cons(element, this)

  def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }

  def map[B](transformer: A=>B): MyList[B] =
     Cons(transformer(h), t.map(transformer))

  def filter(predicate: A=>Boolean): MyList[A] =
    if (predicate(h))  Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def flatMap[B](transformer: A=> MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def ++[B >: A](list: MyList[B]): MyList[B] =  Cons(h, tail ++ list)

  def forEach(f: A => Unit): Unit = {
    f(h)
    t.forEach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B,C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if  (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)
}

trait MyPredicate[-T]{
  def test(elem: T): Boolean
}

trait  MyTransformer[-A, B]{
  def transform(elem: A): B
}

object ListTest extends App {
  val listOfIntegers:MyList[Int] =  Cons(1,  Cons(2,  Cons(3, Empty)))
  val anotherListOfIntegers:MyList[Int] =  Cons(4,  Cons(5, Empty))
  val listOfStrings:MyList[String] =  Cons("Hello",  Cons("Scala", Empty))
  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(_ * 2).toString)
  println(listOfIntegers.filter(_ % 2 == 0).toString)
  println(listOfIntegers ++ anotherListOfIntegers)
  println(listOfIntegers.flatMap((elem: Int) => Cons(elem, Cons(elem + 1, Empty))))
  println(listOfIntegers.sort((x, y) => y - x))
  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))
  println(listOfIntegers.fold(0)(_ + _))

  val combinations = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string
  println(combinations)

}

class EvenPredicate extends MyPredicate[Int] {
  def test(elem: Int): Boolean = elem % 2 == 0
}

class StringToIntTransformer extends MyTransformer[String, Int] {
  def transform(elem: String): Int = elem.toInt
}