package part2abstractMath

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Monads {

  // lists
  val nums = List(1, 2, 3)
  val chars = List('a', 'b', 'c', 'd')

  // TODO 1.1: how do you create all combinations of (number, char)?
  val pairsList = nums.flatMap(num => chars.map(char => (num, char)))
  val pairListFor = for {
    num <- nums
    char <- chars
  } yield (num, char)

  // options
  val numOption = Some(1)
  val charOption = Some('a')

  // TODO 1.2: how do you create the combination of (number, char)?
  val tupleOption = numOption.flatMap(num => charOption.flatMap(char => Option(num, char)))

  // futures
  val numFuture = Future(1)
  val charFuture = Future('a')

  val tupleFuture: Future[(Int, Char)] = for {
    num <- numFuture
    char <- charFuture
  } yield (num, char)

  /*
     Pattern
        - wrapping a value into a M value
            Option(2)
            Future(1)
            List(3)
        - the flatMap mechanism

   */

  // trait MyMonad

  trait MyFunctor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B)
  }
  trait MyMonad[M[_]] {
    def pure[A](value: A): M[A]
    def flatMap[A, B](ma: M[A])(f: A => M[B]): M[B]

    def map[A, B](ma: M[A])(f: A => B): M[B] =
      flatMap(ma)(a => pure(f(a)))
  }


  // Cats Monad
  import cats.Monad
  import cats.instances.list._
  import cats.instances.option._

  val listMonad = Monad[List].flatMap(List(1, 2, 3))(_ => List(1))
  val listPure = Monad[List].pure(1)

  val optionMonad = Monad[Option].flatMap(Option(1))(_ => Option(2))
  val optionPure = Monad[Option].pure(1) // Option(1)

  // TODO 2: use a Monad[Future]
  import cats.instances.future._

  val futureMonad = Monad[Future].flatMap(Future(1))(_ => Future(2))
  val futurePure = Monad[Future].pure(1) // Future(1)

  // specialized API

  // generalize

  // extension methods - weird imports - pure, flatMap

  // TODO 3: implement the map method in MyMonad
  // Monads extend Functors

  // for-comprehensions

  // TODO 4: implement a shorter version of getPairs using for-comprehensions

  def main(args: Array[String]): Unit = {
    println(listPure)
  }
}
