package part1intro

trait JsonSerializer[T]
object TypeClasses {

  case class Person(name: String, age: Int)

  // part 1 - type class definition
  trait JsonSerializer[T] {
    def toJson(value: T): String
  }

  // part 2 - create implicit type class INSTANCES

  implicit object IntSerializer extends JsonSerializer[Int] {
    override def toJson(value: Int): String = value.toString
  }

  implicit object StringSerializer extends JsonSerializer[String] {
    override def toJson(value: String): String = "\"" + value + "\""
  }

  implicit object PersonSerializer extends JsonSerializer[Person] {
    override def toJson(value: Person): String =
      s"""
        |{"name": "${value.name}", "age": ${value.age}}
        |""".stripMargin.trim
  }

  // part 3 - offer some API
  def convertListToJson[T](values: List[T])(implicit serializer: JsonSerializer[T]): String = {
    values.map(value => serializer.toJson(value)).mkString("[", ",", "]")
  }

  // part 4 - extending the existing types via extension method
  object JsonSyntax {
    implicit class JsonSerializable[T](value: T)(implicit serializer: JsonSerializer[T]) {
      def toJson: String = serializer.toJson(value)
    }
  }

  def main(args: Array[String]): Unit = {
//    println(convertListToJson(List(Person("David", 42), Person("Boris", 36))))
    val bob = Person("bob", 13)

    import JsonSyntax._
    println(bob.toJson)
  }

}
