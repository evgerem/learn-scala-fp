package part2abstractMath

import cats.Monoid


object Monoids {

  // Semigroup limitation

  // Monoids

  // extension method for monoid

  // TODO 1: implement a combine fold
  def combineByFold[T](list: List[T])(implicit monoid: Monoid[T]): T = ???

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

  // TODO 3
  case class ShoppingCart(items: List[String], total: Double)
  def checkout(shoppingCart: List[ShoppingCart]): ShoppingCart = ???


  def main(args: Array[String]): Unit = {

  }

}
