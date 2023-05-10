package lectures.part2oop

object Inheritance extends App {

  class Animal {
    val creatureType = "wild"
    def eat = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  class Dog(override val creatureType: String) extends Animal {
    override def eat: Unit = {
      super.eat
      println("crunch, crunch")
    }
  }

  val unknownAnimal = new Dog("K9")
  unknownAnimal.eat
}
