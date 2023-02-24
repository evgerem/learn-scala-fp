package part2abstractMath

import cats.data.OptionT

object MonadTransformers {

  // TODO 1: Implement sumAllOptions
  def sumAllOptions(values: List[Option[Int]]): Int =
    values.foldLeft(0)((acc, curr) => curr match {
      case Some(num) => acc + num
      case None => acc
    }
  )

  def mapOverOptions(values: List[Option[Int]], fn: Int => String): List[Option[String]] =
    values.map(maybeNum => maybeNum match {
      case Some(value) => Some(fn(value))
      case None => None
    })

  // OptionT
  val listOfNumberOptions: OptionT[List, Int] = OptionT(List(Option(1), Option(2), Option(3), None))

  def main(args: Array[String]): Unit = {
    println(listOfNumberOptions.value)
  }
}
