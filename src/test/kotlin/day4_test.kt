import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class day4_test: FunSpec({

    test("pt1 with test input") {
        day4.pt1(readLines("day4_test.txt")) shouldBe 13
    }

    test("pt1 with real input") {
        day4.pt1(readLines("day4_real.txt")) shouldBe 25231
    }

    test("pt2 with test input") {
        day4.pt2(readLines("day4_test.txt")) shouldBe 30
    }

    test("pt2 with real input") {
        day4.pt2(readLines("day4_real.txt")) shouldBe 9721255
    }

})