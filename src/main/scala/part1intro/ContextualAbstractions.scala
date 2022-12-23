package part1intro

import jdk.jfr.DataAmount

import scala.concurrent.duration.DurationInt


object ContextualAbstractions {

  // 0. case classes
  class Pet
  case class Cat(name: String) extends Pet
  case class Dog(name: String) extends Pet

  def sayName(pet: Pet): String = pet match {
    case Cat(name) => name
    case Dog(name) => name
  }

  // 1. implicit classes
  case class Person(name: String) {
    def greet(): String = s"hello, my name is $name"
  }

  implicit class ImpersonableName(name: (String, String)) {
    def greet(): String = name._1 + name._2
  }

  // extension method
  val p = ("Bob", "Alice").greet() // new ImpersonableName("Bob").greet()

  // example: scala.concurrent.duration
  val timeout = 2.seconds

  // 2. implicit arguments and values
  def increment(value: Int)(implicit amount: Int): Int = value + amount

  // more complex example (JsonSerializer, convertToJson)
  trait JsonSerializer[T] {
    def toJson(value: T): String
  }

  def convertToJson[T](value: T)(implicit serializer: JsonSerializer[T]): String = {
    serializer.toJson(value)
  }

  implicit val personSerializer: JsonSerializer[Person] = new JsonSerializer[Person] {
    override def toJson(person: Person): String = s"""{"name": "${person.name}"}"""
  }

  // 3. implicit defs (createListSerializer)
  implicit def createListSerializer[T](implicit serializer: JsonSerializer[T]): JsonSerializer[List[T]] = {
    new JsonSerializer[List[T]] {
      override def toJson(values: List[T]): String =
        values.map(value => serializer.toJson(value)).mkString("[", ",", "]")
    }
  }

  // implicit conversions (not recommended) (stringToCat)
  case class Fox(name: String) {
    def whatDoesTheFoxSay(): String = s"$name says: yayayayaayaa"
  }
  implicit def stringToFox(name: String): Fox = Fox(name)


  // Scope Rule: An inserted implicit conversion must be in scope as a single
  // identifier, or be associated with the source or target type of the conversion.

  def main(args: Array[String]): Unit = {
    println(
      "Fox Joe".whatDoesTheFoxSay()
    )
  }
}
