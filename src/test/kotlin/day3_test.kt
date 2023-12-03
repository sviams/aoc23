import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class day3_test: FunSpec({

    test("pt1 with test input") {
        day3.pt1(readLines("day3_test.txt")) shouldBe 4361
    }

    test("pt1 with real input") {
        day3.pt1(readLines("day3_real.txt")) shouldBe 531561
    }

    test("pt2 with test input") {
        day3.pt2(readLines("day3_test.txt")) shouldBe 467835
    }

    test("pt2 with real input") {
        day3.pt2(readLines("day3_real.txt")) shouldBe 83279367
    }

})