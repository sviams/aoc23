import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class day1_test: FunSpec({

    listOf(
        "1abc2" to "12",
        "a1b2c3d4e5f" to "15",
        "treb7uchet" to "77"
    ).forEach {
        test("first and last digit in a is") {
            day1.firstAndLast(it.first) shouldBe it.second
        }
    }

    test("pt1 with test input") {
        day1.pt1(readLines("day1_test.txt")) shouldBe 142
    }

    test("pt1 with real input") {
        day1.pt1(readLines("day1_real.txt")) shouldBe 54159
    }

    test("pt2 with real input") {
        day1.pt2(readLines("day1_real.txt")) shouldBe 53866
    }

})