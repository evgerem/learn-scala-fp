package part1intro

object TypeClassVariance {


  import cats.Monoid
  import cats.instances.int._
  import cats.instances.option._ // Monoid[Option[Int]]
  import cats.syntax.monoid._

  val res = Option(4) |+| Option(6)

//   val res2 = Some(4) |+| None

  // variance
  class Animal
  class Cat extends Animal

  // covariant type: subtyping is propagated to the generic type
  class Cage[+T]
  val cage: Cage[Animal] = new Cage[Cat] // Cat <: Animal, Cage[Cat] <: Cage[Animal]

  // contravariant type: subtyping is propagated BACKWARDS to the generic type
  class Vet[-T]
  val vet: Vet[Cat] = new Vet[Animal] // Cat <: Animal, Vet[Animal] <: Vet[Cat]

  // rule of thumb: "HAS a T" - covariant, "ACTS on T" - contravariant

  // SoundMaker
  trait SoundMaker[-T]
  implicit object AnimalSoundMaker extends SoundMaker[Animal]
  implicit object CatSoundMaker extends SoundMaker[Cat]
  def makeSound[T](implicit maker: SoundMaker[T]): Unit = println("woho")
  makeSound[Animal]
  // makeSound[Cat]

  // OptionSoundMaker
  implicit object OptionSoundMaker extends SoundMaker[Option[Int]]
  makeSound[Option[Int]]
  makeSound[Some[Int]]

  // covariant TC
  // AnimalShow
  trait AnimalShow[+T] {
    def show: String
  }

  implicit object GeneralAnimalShow extends AnimalShow[Animal] {
    override def show: String = "animals"
  }

  implicit object CatsShow extends AnimalShow[Cat] {
    override def show: String = "cats"
  }

  def organiseShow[T](implicit event: AnimalShow[T]): String = event.show


  // use "smart" constructors (eg. Option)

  def main(args: Array[String]): Unit = {
//    println(organiseShow[Animal])
    println(organiseShow[Cat])
  }

}
