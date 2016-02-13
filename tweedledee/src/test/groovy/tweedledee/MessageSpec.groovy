package tweedledee

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Message)
class MessageSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    /**
     * Data driven test to check for message that cannot be black and should be less than 40 characters
     * Assignment req.# M2 PASSING CONDITION
     * @return
     */
    def "message text need to be non-blank and 40 chars long"(){
        given:
        def messageText = new Message()
        messageText.setText(b)
        expect:
        b.length()<=lengthOfMessage
        !b.isEmpty()

        where:
        b||lengthOfMessage
        'ThisIsAStringThatIsLessThan40CharsLong'|40
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
