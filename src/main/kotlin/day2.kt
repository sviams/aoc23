object day2 {

    data class DrawSet(val red: Int, val green: Int, val blue: Int)
    data class Game(val id: Int, val sets: List<DrawSet>) {
        val power: Int by lazy { sets.maxOf { it.red } * sets.maxOf { it.blue } * sets.maxOf { it.green } }
        fun withinLimit(r: Int, b: Int, g: Int): Boolean = sets.all { set ->
            set.red <= r && set.blue <= b && set.green <= g
        }
    }

    fun parseInput(input: List<String>): List<Game> = input.fold(emptyList()) { games, line ->
        val (gameId) = Regex("""Game (\d+):""").find(line)!!.destructured
        val drawSets = line.split(": ")[1].split(';').fold(emptyList<DrawSet>()) { drawSets, setString ->
            val red = Regex("""(\d+) red""").find(setString)?.groupValues?.last()?.toInt() ?: 0
            val blue = Regex("""(\d+) blue""").find(setString)?.groupValues?.last()?.toInt() ?: 0
            val green = Regex("""(\d+) green""").find(setString)?.groupValues?.last()?.toInt() ?: 0
            drawSets + DrawSet(red, green, blue)
        }
        games + Game(gameId.toInt(), drawSets)
    }

    fun pt1(input: List<String>): Int = parseInput(input).filter { it.withinLimit(12, 14, 13) }.sumOf { it.id }

    fun pt2(input: List<String>): Int = parseInput(input).sumOf { it.power }
}