package part3datamanipulation

object Evaluation {

  import cats.Eval

  val instantEval = Eval.now {
    println("Computing now!")
    42
  }

  val alwaysEval = Eval.always {
    println("Computing again")
    43
  }

  val laterEval = Eval.later {
    println("Computing later!")
    44
  }

  val res = for {
    a <- instantEval
    b <- alwaysEval
  } yield a + b

  def main(args: Array[String]): Unit = {
    println(res.value)
  }

}
