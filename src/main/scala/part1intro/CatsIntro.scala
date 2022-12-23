package part1intro

object CatsIntro {

  // Eq
  val aComparison = 2 == "a string"


  // part 1 - type class import
  import cats.Eq

  // part 2 - import TC instances for the types you need
  import cats.instances.int._

  // part 3 - use the TC API
  val intEq = Eq[Int]
  val res = intEq.eqv(3, 2) // false

  // part 4 - use extension methods
  import cats.syntax.eq._

  val res2 = 3 === 2

  // part 5 - extending the TC operations to composite types, e.g. lists
  import cats.instances.list._
  val res3 = List(1, 2, 3) === List(1)

  // part 6 - create a TC instance for a custom type
  case class Person(name: String, age: Int)
  implicit val personInstance = Eq.instance[Person] { (a, b) =>
    a.name == b.name
  }


  def main(args: Array[String]): Unit = {
    println(
      Person("David", 42) === Person("David", 43)
    )
  }

}
