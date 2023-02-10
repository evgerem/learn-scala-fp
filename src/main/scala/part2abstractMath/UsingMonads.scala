package part2abstractMath

object UsingMonads {

  // either
  val strOrInt: Either[String, Int] = Left("dsfdf")

  type LoadingOr[T] = Either[String, T]
  type ErrorOr[T] = Either[Throwable, T]

  import cats.Monad
  import cats.instances.either._

  val errorMonad = Monad[ErrorOr]


  val error: ErrorOr[Int] = errorMonad.pure(10)
    .map(_ + 10)

  // imaginary online store
  case class OrderStatus(orderId: Long, status: String)
  def getOrderStatus(orderId: Long): LoadingOr[OrderStatus] =
    Right(OrderStatus(orderId, "Ready to ship"))

  def trackLocation(orderStatus: OrderStatus): LoadingOr[String] =
    if (orderStatus.orderId > 1000) Left("Not available")
    else Right("Amsterdam")

  val orderId = 32L
  val location = for {
    ordersStatus <- getOrderStatus(orderId)
    _ = println(s"order status: $ordersStatus")
    location <- trackLocation(ordersStatus)
    _ = println(s"location: $location")
  } yield location

  // TODO: the service layer API of a web app
  case class Connection(host: String, port: String)
  val config = Map(
    "host" -> "localhost",
    "port" -> "4040"
  )

  trait HttpService[M[_]] {
    def getConnection(cfg: Map[String, String]): M[Connection]
    def issueRequest(connection: Connection, payload: String): M[String]
  }
  // DO NOT CHANGE THE CODE

  /*
  Requirements:
  - if the host and port are found in the configuration map, then we'll return a M containing a connection with those values
    otherwise the method will fail, according to the logic of the type M
    (for Try it will return a Failure, for Option it will return None, for Future it will be a failed Future, for Either it will return a Left)
  - the issueRequest method returns a M containing the string: "request (payload) has been accepted", if the payload is less than 20 characters
    otherwise the method will fail, according to the logic of the type M
  TODO: provide a real implementation of HttpService using Try, Option, Future, Either
 */


  def main(args: Array[String]): Unit = {

  }
}
