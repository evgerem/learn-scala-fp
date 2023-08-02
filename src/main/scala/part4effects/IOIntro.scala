package part4effects

import cats.effect.IO
import cats.effect.unsafe.implicits.global

import scala.io.StdIn

object IOIntro {

  val ourFirstIO: IO[Int] = IO.pure {
    42
  }

  val aDelayedIO: IO[Int] = IO.delay {
    println("side effect")
    54
  }

  val aDelayedIO_v2: IO[Int] = IO {
    println("side effect")
    54
  }

  val imporevedIO = aDelayedIO_v2.map(_ * 2)

  def smallProgram(): IO[Unit] = for {
    line1 <- IO(StdIn.readLine())
    line2 <- IO(StdIn.readLine())
    _ <- IO(println(line1 + line2))
  } yield ()

  import cats.syntax.apply._
  val combined: IO[Int] = (ourFirstIO, aDelayedIO).mapN(_ + _)

  def smallProgram_v2(): IO[Unit] =
    (IO(StdIn.readLine()), IO(StdIn.readLine())).mapN(_ + _).map(println)

  def main(args: Array[String]): Unit = {
    val ioa = IO {
      println("A")
      10
    }
    val iob = IO {
      println("B")
      99
    }

    println(forever_v3(IO {
      println("forever")
    }).unsafeRunSync())
  }

  /**
   * Exercises
   */

  // 1 - sequence two IOs and take the result of the LAST one
  // hint: use flatMap
  def sequenceTakeLast[A, B](ioa: IO[A], iob: IO[B]): IO[B] =
    ioa.flatMap(_ => iob)

  def sequenceTakeLast_v2[A, B](ioa: IO[A], iob: IO[B]): IO[B] =
    ioa *> iob // "andThen"

  def sequenceTakeLast_v3[A, B](ioa: IO[A], iob: IO[B]): IO[B] =
    ioa >> iob // "andThen" with by-name call

  // 2 - sequence two IOs and take the result of the FIRST one
  // hint: use flatMap and map
  def sequenceTakeFirst[A, B](ioa: IO[A], iob: IO[B]): IO[A] =
    ioa.flatMap(a => iob.map(_ => a))

  def sequenceTakeFirst_v2[A, B](ioa: IO[A], iob: IO[B]): IO[A] =
    ioa <* iob

  // 3 - repeat an IO effect forever
  // hint: use flatMap + recursion
  def forever[A](io: IO[A]): IO[A] =
    io.flatMap(_ => forever(io))

  def forever_v2[A](io: IO[A]): IO[A] =
    io >> forever(io)

  def forever_v3[A](io: IO[A]): IO[A] =
    io *> forever(io)

  def forever_v4[A](io: IO[A]): IO[A] =
    io.foreverM

  // 4 - convert an IO to a different type
  // hint: use map
  def convert[A, B](ioa: IO[A], value: B): IO[B] =
    ioa.map(_ => value)

  def convert_v2[A, B](ioa: IO[A], value: B): IO[B] =
    ioa.as(value)


  // 5 - discard value inside an IO, just return Unit
  def asUnit[A](ioa: IO[A]): IO[Unit] =
    ioa.map(_ => ())

  def asUnit_v2[A](ioa: IO[A]): IO[Unit] =
    ioa.as(())

  def asUnit_v3[A](ioa: IO[A]): IO[Unit] =
    ioa.void

  // 6 - fix stack recursion
  def sum(n: Int): Int =
    if (n <= 0) 0
    else n + sum(n - 1)

  def sumIO(n: Int): IO[Int] =
    ???

  // 7 (hard) - write a fibonacci IO that does NOT crash on recursion
  // hints: use recursion, ignore exponential complexity, use flatMap heavily
  def fibonacci(n: Int): IO[BigInt] =
    ???


}
