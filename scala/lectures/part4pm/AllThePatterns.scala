package lectures.part4pm

import excercises.{Cons, MyList, Empty}

object AllThePatterns extends App {

  // 1 - constants
  val x:Any  = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ => "Whatever"
  }

  //  2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  //  3 - tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1,1) => "something"
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1,(2,3))
  val matchANestedTuple = nestedTuple match {
    case (_,(2,v)) => s"I've found $v"
  }

  // 4 - case classes - constructor pattern
  val aList: MyList[Int] = Cons(1,Cons(2,Empty))
  val matchAList = aList match {
    case Empty => "Empty"
    case Cons(head,tail) => "Not empty"
  }

  // 5 - list patterns
  val aStandardList = List(1,2,3,4)
  val standardListMatching = aStandardList match {
    case List(1,_,_,_) => "something"
    case List(1,2,_*) => "list of arbitrary length"
    case 1 :: List(_) => "infix pattern"
    case List(1,2,3) :+ 42 => "infix pattern"
  }

  // 6 - type specifiers
  val unknown:Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => "list"
    case _ => "unknown"
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_,_) => "name binding"
    case Cons(1, rest @ Cons(2,_)) => "name binding"
  }

  // 8 - multi-patterns
  val multipattern = aList match {
    case Empty | Cons(0, _) => "compound pattern"
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => "special"
  }

  //Type erasure, JVM trick question
  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "list of strings"
    case listOfNumbers: List[Int] => "list of numbers"
    case _ => ""
  }


}
