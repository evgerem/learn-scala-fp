package part4effects

import cats.effect.IO
import cats.effect.unsafe.implicits.global

object IOIntro {


  /**
   * Exercises
   */

  // 1 - sequence two IOs and take the result of the LAST one
  // hint: use flatMap
  def sequenceTakeLast[A, B](ioa: IO[A], iob: IO[B]): IO[B] =
    ???

  def sequenceTakeLast_v2[A, B](ioa: IO[A], iob: IO[B]): IO[B] =
    ???

  def sequenceTakeLast_v3[A, B](ioa: IO[A], iob: IO[B]): IO[B] =
    ???

  // 2 - sequence two IOs and take the result of the FIRST one
  // hint: use flatMap
  def sequenceTakeFirst[A, B](ioa: IO[A], iob: IO[B]): IO[A] =
    ???

  def sequenceTakeFirst_v2[A, B](ioa: IO[A], iob: IO[B]): IO[A] =
    ???

  // 3 - repeat an IO effect forever
  // hint: use flatMap + recursion
  def forever[A](io: IO[A]): IO[A] =
    ???

  def forever_v2[A](io: IO[A]): IO[A] =
    ???

  def forever_v3[A](io: IO[A]): IO[A] =
    ???

  def forever_v4[A](io: IO[A]): IO[A] =
    ???

  // 4 - convert an IO to a different type
  // hint: use map
  def convert[A, B](ioa: IO[A], value: B): IO[B] =
    ???

  def convert_v2[A, B](ioa: IO[A], value: B): IO[B] =
    ???

  // 5 - discard value inside an IO, just return Unit
  def asUnit[A](ioa: IO[A]): IO[Unit] =
    ???

  def asUnit_v2[A](ioa: IO[A]): IO[Unit] =
    ???

  def asUnit_v3[A](ioa: IO[A]): IO[Unit] =
    ???

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

  def main(args: Array[String]): Unit = {
    val anIO = IO {
      println("go!")
    }

    anIO.unsafeRunSync()
  }
}
