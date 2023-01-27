package part2abstractMath

import cats.Functor

object Functors {

  // map in list, option, try

  // simplified definition

  // Cats Functor

  // generalizing an API

  // generalize

  // TODO 1: define your own functor for a binary tree
  // hint: define an object which extends Functor[Tree]
  trait Tree[+T]
  object Tree {
    def leaf[T](value: T): Tree[T] = Leaf(value)
    def branch[T](value: T, left: Tree[T], right: Tree[T]): Tree[T] =
      Branch(value, left, right)
  }
  case class Leaf[+T](value: T) extends Tree[T]
  case class Branch[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]

  implicit val treeFunctor = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] =
      fa match {
        case Leaf(value) => Leaf(f(value))
        case Branch(value, left, right) => Branch(f(value), map(left)(f), map(right)(f))
      }
  }

  // extension method - map
  import cats.syntax.functor._

  // TODO 2: write a shorted do10x method using extension methods
  def do10xShort[F[_]: Functor](fa: F[Int]): F[Int] =
    fa.map(_ * 10)

  def main(args: Array[String]): Unit = {
    val aTree = Tree.branch(1, Tree.leaf(2), Tree.leaf(3))
    println(do10xShort(aTree))
  }
}
