package part2abstractMath

import cats.Monoid


object Monoids {
  import cats.Semigroup
  import cats.instances.int._
  import cats.instances.string._
  import cats.syntax.semigroup._

  // Semigroup limitation
  val ints = (1 to 10).toList
  val sumInts = ints.foldLeft(0)((a, b) => a |+| b)

  // Monoids
  import cats.Monoid
  val intMonoid = Monoid[Int]
  val intResult = intMonoid.combine(44, 3)
  val zero = intMonoid.empty

  // extension method for monoid |+|

  // TODO 1: implement a combine fold
  def combineByFold[T](list: List[T])(implicit monoid: Monoid[T]): T = list.foldLeft(monoid.empty)(_ |+| _)
  def combineByFold2[T](list: List[T])(implicit monoid: Monoid[T]): T = {
    if (list.isEmpty) monoid.empty
    else list.head |+| combineByFold2(list.tail)
  }


  // TODO 2: combine a list of phonebooks as Map[String, Int]
  val phonebooks = List(
    Map(
      "Alice" -> 111,
      "Bob" -> 222
    ),
    Map(
      "Foo" -> 333,
      "Bar" -> 444
    ),
    Map(
      "Frank" -> 555
    )
  )
  import cats.instances.map._

  // TODO 3
  case class ShoppingCart(items: List[String], total: Double)
  def checkout(shoppingCart: List[ShoppingCart]): ShoppingCart = ???


  def main(args: Array[String]): Unit = {
    val strs = ints.map(_.toString)
    println(combineByFold(phonebooks))
  }

}
