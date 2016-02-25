import java.util.*

fun main(args: Array<String>) {
  testAdd(10, 23)
  testAdd(1, 457)
  testAdd(89, 457)
  testAdd(789, 457)
}

fun testAdd(a: Int, b: Int) {
  println("${a + b} -- ${format(add(toList(a), toList(b)))}")
}

fun toList(a: Int): List<Int> {
  var t = a
  val digits = ArrayList<Int>()
  while (t > 0) {
    digits.add(t % 10)
    t /= 10
  }
  return digits
}

fun format(a: List<Int>): String {
  var s = ""
  for (d in a.reversed())
    s += d
  return "<$s $a>"
}

fun max(a: Int, b: Int) = if (a > b) a else b

fun List<Int>.safeGet(i: Int) = if (i < size) this[i] else 0

fun ArrayList<Int>.safeAddTo(i: Int, term: Int) {
  if (term == 0) return

  if (i >= size) this.add(0)
  this[i] += term
}

fun add(a: List<Int>, b: List<Int>): List<Int> {
  //println("${format(a)} ${format(b)}")

  val result = ArrayList<Int>()
  for (i in 0..max(a.lastIndex, b.lastIndex))
    result.add(a.safeGet(i) + b.safeGet(i))

  for (i in result.indices) {
    result.safeAddTo(i + 1, result[i] / 10)
    result[i] %= 10
  }

  return result
}

