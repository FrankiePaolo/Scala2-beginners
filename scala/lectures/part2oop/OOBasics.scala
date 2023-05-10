package lectures.part2oop

object OOBasics extends App {
  val person = Person("John", 26)
  println(person.age)

  val author = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  val counter = new Counter(0)
  println(counter.inc.count)
  counter.inc.inc.inc.print
  counter.inc(2).print
}

case class Person(name: String, age: Int)

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName: String = s"$firstName $surname"
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge = year - author.year
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Counter(val count: Int) {
  def inc = {
    println("incrementing")
    new Counter(count + 1)
  }
  def dec = {
    println("decrementing")
    new Counter(count - 1)
  }
  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }
  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print = println(count)
}