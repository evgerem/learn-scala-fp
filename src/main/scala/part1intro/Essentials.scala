package part1intro

import scala.util._

object Essentials {

  // values
  val a = 43

  // expressions
  val res: String = if (a == 43) "good" else "bad"


  // OOP
  class Animal
  class Cat extends Animal

  trait EatsMeat {
    def eat(other: String): Unit
  }
  class Crocodile(val name: String) extends Animal with EatsMeat {
    override def eat(other: String): Unit = println(s"eaten: $other")
  }

  // signleton
  object Foo {
    def foo() = println("foo")
  }
  // companions object
  object Crocodile {
    def apply(name: String): Crocodile = {
      new Crocodile(name)
    }
  }

  // generics
  class MyList[A] {
    def print(a: A): Unit = {
      println(a)
    }
  }

  val aList = List(1, 2)


  // method notation
  val adding = 1 + 2
  val otherAdding = 1.+(2)

  class Num(val value: Int) {
    def add(b: Num): Num = new Num(value + b.value)

    def +(b: Num): Num = add(b)

    override def toString: String = s"Num($value)"
  }

  // functions as values
  val incrementor: Int => Int = x => x + 1
  val incremented = incrementor(5) // 6

  // map, flatMap, filter, reduce, foldLeft, foldRight
  val checkerBoard: List[(Char, Int)] = List(1, 2, 3)
    .flatMap(n => List('a', 'b', 'c').map(c => (c, n)))

  // for-comprehensions
  val checkerBoardWithFor: List[(Char, Int)] = for {
    n <- List(1, 2, 3)
    c <- List('a', 'b', 'c')
  } yield (c, n)

  // options and try
  val opt: Option[Int] = None
  val optMapped = opt.flatMap(x => Some(x + 1))

  val aTry: Try[Int] = Try { // Failure, Success
    throw new RuntimeException("bad stuff")
    4
  }
  val mappedTry = aTry.map(_ + 1)

  // pattern matching (on int, option, try)
  val tryResult: String = aTry match {
    case Failure(exception) => s"error $exception"
    case Success(value) => s"success: $value"
  }

  val tryResultOption: Option[Int] = aTry match {
    case Failure(_) => None
    case Success(value) => Some(value)
  }

  val resOpt: Int = tryResultOption match {
    case Some(v) => v
    case None => -1
  }


  val mol = 42
  var matchOnInt = mol match {
    case 1 => "bad"
    case 42 => "meaning of life"
    case _ => "default"
  }

  // Futures + map a future

  // partial functions

  // higher kinded types
  trait SomeSeq[F[_]] {
    def sequential: Boolean
  }
  val seq = new SomeSeq[List] {
    override def sequential = true
  }

  def main(args: Array[String]): Unit = {

    val tokenClaim: java.lang.Long = null
    val opt: Option[Long] = Option(tokenClaim).map(javaLong => javaLong: Long)

    val otherClaim: Long = tokenClaim
    println(otherClaim)
  }

}
