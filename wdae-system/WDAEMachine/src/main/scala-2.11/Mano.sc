val a = List(1, 2, 3)

3 :: a
a

val b = Set(1, 2, 3)
b + 4
b

var c = Map("foo" -> "bar", "toto" -> "tata")
c += "toto" -> "lol"
c

var devices: Set[Int] = Set(1, 2, 3)
val foo = devices
devices += 4
foo
devices