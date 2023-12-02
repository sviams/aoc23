import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class day2_test: FunSpec({

    test("pt1 with test input") {
        day2.pt1(readLines("day2_test.txt")) shouldBe 8
    }

    test("pt1 with real input") {
        day2.pt1(readLines("day2_real.txt")) shouldBe 2679
    }

    test("pt2 with test input") {
        day2.pt2(readLines("day2_test.txt")) shouldBe 2286
    }

    test("pt2 with real input") {
        day2.pt2(readLines("day2_real.txt")) shouldBe 77607
    }

})