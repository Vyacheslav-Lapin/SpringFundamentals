import core.HelloMessageGenerator;

class GroovyHelloMessageGenerator implements HelloMessageGenerator {
    String toString() {
        "Фёдор Владимирович Емельяненко" // Заменить вторую букву на "ё" для прохождения теста
    }
}
