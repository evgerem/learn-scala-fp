package part3datamanipulation

object Readers {

  /*
    - configuration file => initial data structure
    - a DB layer
    - an HTTP layer
    - a business logic layer
   */
  case class Configuration(dbUsername: String, dbPassword: String, host: String, port: Int)

  case class DbConnection(username: String, password: String) {
    def getOrderStatus(orderId: Long): String = "dispatched" // select * from the db table and return the status of the orderID

    def getLastOrderId(username: String): Long = 542643 // select max(orderId) from table where username = username
  }
  case class HttpService(host: String, port: Int) {
    def start(): Unit = println("server started") // this would start the actual server
  }
  val config = Configuration("brandi", "abc", "localhost", 8080)

  // cats Reader
  import cats.data.Reader

  val dbReader: Reader[Configuration, DbConnection] = Reader(config => {
    DbConnection(config.dbUsername, config.dbPassword)
  })

  val httpServiceReader: Reader[Configuration, HttpService] = Reader(config => HttpService(config.host, config.port))

  val status = dbReader
    .map(db => db.getLastOrderId("evg"))
    .flatMap(orderId => dbReader.map(db => db.getOrderStatus(orderId)))

  val resReader = for {
    orderId <- dbReader.map(_.getLastOrderId("evg"))
    status <- dbReader.map(_.getOrderStatus(orderId))
  } yield status

  def main(args: Array[String]): Unit = {
    println(resReader.run(config))
  }
}
