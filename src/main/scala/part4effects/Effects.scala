package part4effects

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Effects {

  // pure functional programming
  // substitution
  def combine(a: Int, b: Int): Int = a + b
  val five = combine(2, 3)
  val five_v2 = 2 + 3
  val five_v3 = 5

  // referential transparency = can replace an expression with its value without changing the program's behavior

  // example: print to console
//  val printSomething: Unit = println("Cats Effect")
  val printSomething_v2: Unit = ()

  var a = 0
  val changeVar: Unit = (a += 1)
  val changeVar_v2: Unit = ()

  /** Effect types
    * Properties:
    * - type signature describes the kind of calculation that will be preformed
    * - type signature describes the VALUE that will be calculated
    * - when side effects are needed, effect construction is separate for effect execution
    */

  /* Option
       - describes a possible absent value
       - computes value A if it exists
       - side effects are not needed
   */

  val anOption: Option[Int] = Option(42)

  /* Future
        - describes a value that will be computed in the future
        - computes value A if it successfully completes
        - side effects are needed, execution is NOT separate from construction
   */
  val aFuture: Future[Int] = Future(42)

  /* MyIO
  - describes a computation that may have side effects
  - calculates a value A if it successfull
  - side effects are required for the evaluation of () => A
    - creation of MyIO does not produce side effects
  */

  case class MyIO[A](unsafeRun: () => A) {
    def map[B](f: A => B): MyIO[B] = MyIO(() => f(unsafeRun()))
    def flatMap[B](f: A => MyIO[B]): MyIO[B] =
      MyIO(() => f(unsafeRun()).unsafeRun())
  }

  val anIO: MyIO[Int] = MyIO(() => {
    println("I am computing a value")
    Thread.sleep(1000)
    42
  })

  /** Exercises
   *  1. An IO which returns the current time of the system (System.currentTimeMillis)
   *  2. An IO which measures the duration of a computation (hint: use ex 1)
   *  3. An IO which prints something to the console
   *  4. An IO which reads a line (a string) from the std input
   */

  // 1
  val clock: MyIO[Long] = MyIO(() => System.currentTimeMillis())

  // 2
  def measure[A](computation: MyIO[A]): MyIO[Long] = for {
    start <- clock
    _ <- log("start")
    _ <- computation
    stop <- clock
    _ <- log("stop")
  } yield stop - start

  // 3
  def log(msg: String): MyIO[Unit] = {
    MyIO(() => println(msg))
  }

  // 4
  val read: MyIO[String] = MyIO(() => {
    println("Enter a string: ")
    scala.io.StdIn.readLine()
  })

  val program: MyIO[Unit] = for {
    line1 <- read
    line2 <- read
    _ <- log(line1 + line2)
  } yield ()


  // 1. An IO which returns the current time of the system (System.currentTimeMillis) called foo
  val foo: MyIO[Long] = MyIO(() => System.currentTimeMillis())
  def main(args: Array[String]): Unit = {
    program.unsafeRun()
  }
}

/**  Effect types
  *  Properties:
  *    - type signature describes the kind of calculation that will be preformed
  *    - type signature describes the VALUE that will be calculated
  *    - when side effects are needed, effect construction is separate for effect execution
  */

/** Exercises
  *  1. An IO which returns the current time of the system
  *  2. An IO which measures the duration of a computation (hint: use ex 1)
  *  3. An IO which prints something to the console
  *  4. An IO which reads a line (a string) from the std input
  */
