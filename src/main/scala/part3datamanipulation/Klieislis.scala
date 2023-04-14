package part3datamanipulation

object Klieislis {

  val func1: Int => Option[String] = x => if (x % 2 == 0) Some(s"$x is even") else None
  val func2: Int => Option[Int] = x => Some(x * 3)

  // val func3 = func2 andThen func1

  val plainFunc1: Int => String = x => if (x % 2 == 0) s"$x is even" else "fail"
  val plainFunc2: Int => Int = x => x * 3

  val plainFunc3 = plainFunc2 andThen plainFunc1

  import cats.data.Kleisli
  val func1K: Kleisli[Option, Int, String] = Kleisli(func1)
  val func2K: Kleisli[Option, Int, Int] = Kleisli(func2)

  val multiply = func2K.map(_ * 2)

  import cats.Id
  import cats.data.Reader

  val times2 = Reader[Int, Int](x => x * 2)
  val plus4 = Reader[Int, Int](y => y + 4)



  def main(args: Array[String]): Unit = {
    println(times2.run(10))
  }
}
