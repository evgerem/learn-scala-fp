package part3datamanipulation

object Writers {

  import cats.data.Writer

  val numWriter: Writer[List[String], Int] = Writer(List("Init num"), 42)

  val resWriter = numWriter
    .map(_ + 1)
    .mapWritten(log => log :+ "Incremented")
    .mapBoth((log, value) => (log :+ s"Incremented with ${value}", value + 1))
    .bimap(log => log :+ "bimap", value => value + 1)

  val writerA = Writer(List("Log for 5"), 5)
  val writerB = Writer(List("Log for 6", "other log"), 6)

  val sumWriter = for {
    a <- writerA
    b <- writerB
  } yield a + b

  val emptyWriter = sumWriter.reset

  val (logs, value) = sumWriter.run

  def main(args: Array[String]): Unit = {
    println(emptyWriter)
  }

}
