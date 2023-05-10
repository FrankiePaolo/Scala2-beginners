package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App{
  class Person(val name: String, favoriteMovie: String, val age: Int = 0){
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)
    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)
    def learns(thing: String) = s"$name is learning $thing"
    def learnsScala = this learns "Scala"
    def unary_! : String = s"$name, what the heck?!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)


  // prefix notation

  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-

  println(!mary)
  println(mary.unary_!)

  println(mary())
  println((mary + "the Rockstar")())
  println((+mary).age)
  println(mary.learns("Scala"))
  println(mary learnsScala)
}
