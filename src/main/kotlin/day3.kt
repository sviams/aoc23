import kotlin.math.max
import kotlin.math.min

object day3 {

    data class Number(val chars: String, val startIndex: Int, val row: Int) {
        fun toInt() = chars.toInt()
        val range by lazy { IntRange(startIndex, startIndex+chars.length-1) }
        fun isAdjacentToSymbol(world: List<Line>): Boolean =
            (-1 .. 1).fold(emptyList<Symbol>()) { acc, offset -> acc + getSymbolsInRange(world, row+offset, range) }.any()
    }
    data class Symbol(val char: Char, val col: Int, val row: Int) {
        fun isGearSymbol() = char == '*'
        val range by lazy { IntRange(col-1, col+1) }
        fun adjacentNumbers(world: List<Line>): List<Number> =
            (-1 .. 1).fold(emptyList()) { acc, offset -> acc + getNumbersInRange(world, row+offset, range) }
        fun gearRatio(world: List<Line>): Int {
            val nums = adjacentNumbers(world).map { it.toInt() }
            return nums.first() * nums.last()
        }
    }

    fun getNumbersInRange(world: List<Line>, row: Int, range: IntRange) = if (row < 0 || row >= world.size) emptyList() else world[row].numbers.filter { range.intersect(it.range).any() }
    fun getSymbolsInRange(world: List<Line>, row: Int, range: IntRange) = if (row < 0 || row >= world.size) emptyList() else world[row].symbols.filter { range.intersect(it.range).any() }

    data class Line(val numbers: List<Number>, val symbols: List<Symbol>)

    tailrec fun numbersFromLine(line: String, row: Int, res: List<Number> = emptyList(), currentNum: String = "", currentStart: Int = -1, index: Int = 0): List<Number> {
        if (index == line.length) return if (!currentNum.isEmpty()) res + Number(currentNum, currentStart, row) else res
        val atIndex = line[index]
        val newCurrentNum = if (atIndex.isDigit()) currentNum + atIndex else ""
        val newStart = if (currentNum == "" && atIndex.isDigit()) index else currentStart
        val newRes = if (!atIndex.isDigit() && !currentNum.isEmpty()) res + Number(currentNum, currentStart, row) else res
        return numbersFromLine(line, row, newRes, newCurrentNum, newStart, index+1)
    }

    fun parseInput(input: List<String>) = input.foldIndexed(emptyList<Line>()) { row, linesRes, line ->
        val nums = numbersFromLine(line, row)
        val symbols = line.foldIndexed(emptyList<Symbol>()) { col, acc, c ->
            if (!c.isDigit() && c != '.') acc + Symbol(c, col, row) else acc
        }
        linesRes + Line(nums, symbols)
    }

    fun pt1(input: List<String>): Int {
        val world = parseInput(input)
        return world.fold(0) { acc, line ->
            val hasAdjacent = line.numbers.filter { it.isAdjacentToSymbol(world) }
            acc + hasAdjacent.sumOf { it.toInt() }
        }
    }

    fun pt2(input: List<String>): Int {
        val world = parseInput(input)
        return world.fold(0) { acc, line ->
            val actualGears = line.symbols.filter {it.isGearSymbol() && it.adjacentNumbers(world).size == 2 }
            acc + actualGears.sumOf { it.gearRatio(world) }
        }
    }
}