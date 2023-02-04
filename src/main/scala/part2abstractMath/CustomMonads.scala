package part2abstractMath

import cats.Id
import part2abstractMath.Functors.Tree

object CustomMonads {

  // CustomOption

  // TODO 1: define a monad for the identity type

  // Tree
  trait Tree[+T]
  case class Leaf[+T](value: T) extends Tree[T]
  case class Branch[+T](left: Tree[T], right: Tree[T]) extends Tree[T]



  def main(args: Array[String]): Unit = {
    val aTree: Tree[Int] = Branch(1, Leaf(2), Leaf(3))
  }
}
