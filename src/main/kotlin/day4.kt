import kotlin.math.max
import kotlin.math.min

object day4 {

    data class Card(val id: Int, val winning: List<Int>, val mine: List<Int>) {
        val matches by lazy { mine.intersect(winning) }
        val value by lazy {
            if (matches.isNotEmpty())
                ( 0 until matches.size-1).fold(1) { acc, i -> acc * 2 }
            else 0
        }
        val copies by lazy { matches.indices.fold(emptyList<Int>()) { acc, i ->
            acc.plus(id+i+1)
            }
        }
    }

    fun parseInput(input: List<String>): List<Card> = input.fold(emptyList<Card>()) { acc, line ->
        val (id) = Regex("""Card\s+(\d+):""").find(line)!!.destructured
        val parts = line.split(':').last().split('|')
        val winningA = parts[0].split(' ').filterNot { it.isEmpty() }
        val winning = winningA.map { it.toInt() }
        val mineA = parts[1].split(' ').filterNot { it.isEmpty() }
        val mine = mineA.map { it.toInt() }
        acc + Card(id.toInt(), winning, mine)
    }

    tailrec fun calcValues(unprocessed: List<Card>, result: Map<Int, Int> = emptyMap()): Int {
        if (unprocessed.isEmpty()) return result.values.sum()
        val next = unprocessed.first()
        val v = 1 + next.copies.sumOf { result[it]!! }
        return calcValues(unprocessed.drop(1), result + (next.id to v))
    }

    fun pt1(input: List<String>): Int = parseInput(input).sumOf { it.value }

    fun pt2(input: List<String>): Int = calcValues(parseInput(input).reversed())
}