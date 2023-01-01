package part2abstractMath

// Specific imports:

// import cats.YourTypeClass
// import cats.instances.yourType._
// import cats.syntax.yourTypeClass._

// Everything

// import cats._
// import cats.implicits._

object Semigroups {

  // Semigroups COMBINE elements of the same type
  import cats.Semigroup
  import cats.instances.int._
  val intSemigroup = Semigroup[Int]
  val intsCombined = intSemigroup.combine(4, 5)

  import cats.instances.string._
  val strSemigroup = Semigroup[String]
  val stringsCombined = strSemigroup.combine("hello ", "world")

  // Semigroup int and string

  // Specific API
  def reduceInts(list: List[Int]): Int = list.reduce(intSemigroup.combine)
  def reduceStrings(list: List[String]): String = list.reduce(strSemigroup.combine)

  // General API
  def reduceThings[T](list: List[T])(implicit semigroup: Semigroup[T]): T =
    list.reduce(semigroup.combine)

  // TODO 1: support a new type
  // hint: same as in Eq
//  implicit val personInstance = Eq.instance[Person] { (a, b) =>
//    a.name == b.name
//  }

  case class Expense(id: Long, amount: Double)
  implicit val expenseSemigroupInstance = Semigroup.instance[Expense] { (a, b) =>
    Expense(id = a.id, amount = a.amount + b.amount)
  }

  // extension methods
  import cats.syntax.semigroup._

  val intsSum = 1 |+| 2
  val expenses = Expense(1, 10) |+| Expense(2, 25)
  // TODO 2: implement reduceThings2 with |+|
  def reduceThings2[T](list: List[T])(implicit semigroup: Semigroup[T]): T =
    list.reduce(_ |+| _)

  def main(args: Array[String]): Unit = {
    val ints = (1 to 10).toList
    val strs = ints.map(_.toString)
    val expenses = ints.map(i => Expense(i, i * 1000))

    println(reduceThings2(expenses))
  }

}
