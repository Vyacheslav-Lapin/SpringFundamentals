import core.HelloMessageGenerator;

class GroovyHelloMessageGenerator implements HelloMessageGenerator {
    String toString() {
        "Федор Владимирович Емельяненко" // Заменить вторую букву на "ё" для прохождения теста
    }
}
