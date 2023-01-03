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
  trait Tree[T]
  case class Leaf[T](value: T) extends Tree[T]
  case class Branch[T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]

  // extension method - map

  // TODO 2: write a shorted do10x method using extension methods

  def main(args: Array[String]): Unit = {

  }
}
