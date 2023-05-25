package part4effects

import cats.effect.IO
import cats.effect.unsafe.implicits.global

object IOIntro {
  def main(args: Array[String]): Unit = {
    val anIO = IO {
      println("go!")
    }

    anIO.unsafeRunSync()
  }
}
