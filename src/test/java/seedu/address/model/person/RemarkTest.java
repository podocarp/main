package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void constructor_invalidRemark_throwsIllegalArgumentException() {
        String invalidRemark = "";
        assertThrows(IllegalArgumentException.class, () -> new Remark(invalidRemark));
    }

    @Test
    public void isValidRemark() {
        // null remark
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // blank remark
        assertFalse(Remark.isValidRemark("")); // empty string
        assertFalse(Remark.isValidRemark(" ")); // spaces only

        // missing parts
        assertFalse(Remark.isValidRemark("@example.com")); // missing local part
        assertFalse(Remark.isValidRemark("peterjackexample.com")); // missing '@' symbol
        assertFalse(Remark.isValidRemark("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Remark.isValidRemark("peterjack@-")); // invalid domain name
        assertFalse(Remark.isValidRemark("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Remark.isValidRemark("peter jack@example.com")); // spaces in local part
        assertFalse(Remark.isValidRemark("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Remark.isValidRemark(" peterjack@example.com")); // leading space
        assertFalse(Remark.isValidRemark("peterjack@example.com ")); // trailing space
        assertFalse(Remark.isValidRemark("peterjack@@example.com")); // double '@' symbol
        assertFalse(Remark.isValidRemark("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Remark.isValidRemark("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Remark.isValidRemark("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Remark.isValidRemark("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Remark.isValidRemark("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Remark.isValidRemark("peterjack@example.com-")); // domain name ends with a hyphen

        // valid remark
        assertTrue(Remark.isValidRemark("PeterJack_1190@example.com"));
        assertTrue(Remark.isValidRemark("a@bc")); // minimal
        assertTrue(Remark.isValidRemark("test@localhost")); // alphabets only
        assertTrue(Remark.isValidRemark("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters local part
        assertTrue(Remark.isValidRemark("123@145")); // numeric local part and domain name
        assertTrue(Remark.isValidRemark("a1+be!@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Remark.isValidRemark("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Remark.isValidRemark("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }
}
