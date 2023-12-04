object day4 {

    data class Card(val id: Int, val winning: List<Int>, val mine: List<Int>) {
        val matches by lazy { mine.intersect(winning) }
        val value by lazy {
            if (matches.isEmpty()) 0 else
                ( 0 until matches.size-1).fold(1) { acc, i -> acc * 2 }
        }
        val copies by lazy { matches.indices.fold(emptyList<Int>()) { acc, i -> acc.plus(id+i+1) } }
    }

    fun parseInput(input: List<String>): List<Card> = input.fold(emptyList<Card>()) { acc, line ->
        val (id) = Regex("""Card\s+(\d+):""").find(line)!!.destructured
        val parts = line.split(':').last().split('|')
        val winning = parts[0].split(' ').filterNot { it.isEmpty() }.map { it.toInt() }
        val mine = parts[1].split(' ').filterNot { it.isEmpty() }.map { it.toInt() }
        acc + Card(id.toInt(), winning, mine)
    }

    fun pt1(input: List<String>): Int = parseInput(input).sumOf { it.value }

    fun pt2(input: List<String>): Int = parseInput(input)
        .reversed()
        .fold(emptyMap<Int, Int>()) { res, card -> res + (card.id to (1 + card.copies.sumOf { res[it]!! }))}.values.sum()
}