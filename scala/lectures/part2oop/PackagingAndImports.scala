package lectures.part2oop

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {
  //This method is defined in the package object
  sayHello

  //imports

  // 1. use FQ names
  val date = new Date

  //  2. use aliasing
  val sqlDate = new SqlDate(2018, 5, 4)


}
