import io.kotest.core.test.TestScope

fun String.toLines(): List<String> = this.split("\n").map { it.trimEnd() }

fun TestScope.readLines(name: String) : List<String> = this::class.java.classLoader.getResource(name).readText().toLines()