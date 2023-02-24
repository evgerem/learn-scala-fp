package part2abstractMath

import cats.{Id, Monad}
import part2abstractMath.Functors.Tree

object CustomMonads {

  // CustomOption
  implicit object CustomOptionMonad extends Monad[Option] {
    override def pure[A](x: A): Option[A] = Some(x)

    override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa.flatMap(f)

    override def tailRecM[A, B](a: A)(f: A => Option[Either[A, B]]): Option[B] = ???

  }

  // TODO 1: define a monad for the identity type
  type Identity[T] = T
  implicit object IdentityMonad extends Monad[Identity] {
    override def pure[T](x: T): Identity[T] = x

    override def flatMap[T, B](ft: Identity[T])(f: T => Identity[B]): Identity[B] =
      f(ft)

    override def tailRecM[A, B](a: A)(f: A => Identity[Either[A, B]]): Identity[B] = ???
  }

  // Tree
  trait Tree[+T]
  case class Leaf[+T](value: T) extends Tree[T]
  case class Branch[+T](left: Tree[T], right: Tree[T]) extends Tree[T]



  def main(args: Array[String]): Unit = {
//    val aTree: Tree[Int] = Branch(1, Leaf(2), Leaf(3))
  }
}
