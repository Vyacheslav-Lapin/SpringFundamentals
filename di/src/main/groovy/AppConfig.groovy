import core.MessageGenerator
import core.annotations.AnnotationHelloMessageGenerator
import core.xml.XmlHelloMessageGenerator

def beans = {

    def patronymic = 'Владимирович',
            lastName = 'Емельяненко'

    xmlHelloMessageGenerator (XmlHelloMessageGenerator, firstName: 'Фёдор', lastName: 'Емельяненко') {
        patronymic = ref(patronymic)
    }

    annotationHelloMessageGenerator(AnnotationHelloMessageGenerator)

    javaHelloMessageGenerator(MessageGenerator) {
        { -> "Hello, Фёдор $patronymic $lastName!" }
    }
}
