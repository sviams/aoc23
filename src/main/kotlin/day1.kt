object day1 {

    val digitMap = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun firstAndLast(input: String): String {
        val onlyDigits = input.toCharArray().filter { it.isDigit() }
        val res = "" + onlyDigits.first() + onlyDigits.last()
        return res
    }

    fun replaceSpelledOutDigits(input: String): String {
        val res = input.fold("") { acc, c ->
            val next = acc + c
            if (digitMap.keys.any { next.contains(it) }) {
                digitMap.entries.fold(next) { acc2, (spelledOut, asDigit) ->
                    acc2.replace(spelledOut, asDigit)
                } + c
            } else next
        }
        return res
    }

    fun pt1(input: List<String>): Int = input.map { firstAndLast(it).toInt() }.sum()

    fun pt2(input: List<String>): Int = input.map { firstAndLast(replaceSpelledOutDigits(it)).toInt() }.sum()
}