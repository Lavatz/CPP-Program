package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import contacts.Contact;

class ContactTest {

    @Nested
    class ValidContactTests {
        @Test
        void testContactCreation() {
            Contact contact = new Contact("111", "First", "Last", "1234567890", "222 A St");
            
            assertAll("Contact creation",
                () -> assertEquals("111", contact.getContactId()),
                () -> assertEquals("First", contact.getFirstName()),
                () -> assertEquals("Last", contact.getLastName()),
                () -> assertEquals("1234567890", contact.getPhone()),
                () -> assertEquals("222 A St", contact.getAddress())
            );
        }

        @Test
        void testContactCreationWithBoundaryValues() {
            Contact contact = new Contact("1234567890", "Firstname1", "Lastname10", "1234567890", 
                                         "123 Main St, Apartment 4B");
            
            assertAll("Contact creation with boundary values",
                () -> assertEquals("1234567890", contact.getContactId()),
                () -> assertEquals("Firstname1", contact.getFirstName()),
                () -> assertEquals("Lastname10", contact.getLastName()),
                () -> assertEquals("1234567890", contact.getPhone()),
                () -> assertEquals("123 Main St, Apartment 4B", contact.getAddress())
            );
        }
    }

    @Nested
    class InvalidContactIdTests {
        @Test
        void testContactIdNull() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact(null, "First", "Last", "1234567890", "222 A St");
            });
        }

        @Test
        void testContactIdTooLong() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("11111111111", "First", "Last", "1234567890", "222 A St");
            });
        }

        @Test
        void testContactIdExactlyMaxLength() {
            // Should not throw exception
            Contact contact = new Contact("1234567890", "First", "Last", "1234567890", "222 A St");
            assertEquals("1234567890", contact.getContactId());
        }
    }

    @Nested
    class InvalidFirstNameTests {
        @Test
        void testFirstNameNull() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("111", null, "Last", "1234567890", "222 A St");
            });
        }

        @Test
        void testFirstNameTooLong() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("111", "FirstNameTooLong", "Last", "1234567890", "222 A St");
            });
        }

        @Test
        void testFirstNameExactlyMaxLength() {
            // Should not throw exception
            Contact contact = new Contact("111", "Firstname1", "Last", "1234567890", "222 A St");
            assertEquals("Firstname1", contact.getFirstName());
        }
    }

    @Nested
    class InvalidLastNameTests {
        @Test
        void testLastNameNull() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("111", "First", null, "1234567890", "222 A St");
            });
        }

        @Test
        void testLastNameTooLong() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("111", "First", "LastNameTooLong", "1234567890", "222 A St");
            });
        }

        @Test
        void testLastNameExactlyMaxLength() {
            // Should not throw exception
            Contact contact = new Contact("111", "First", "Lastname10", "1234567890", "222 A St");
            assertEquals("Lastname10", contact.getLastName());
        }
    }

    @Nested
    class InvalidPhoneTests {
        @Test
        void testPhoneNull() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("111", "First", "Last", null, "222 A St");
            });
        }

        @Test
        void testPhoneTooShort() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("111", "First", "Last", "123456789", "222 A St");
            });
        }

        @Test
        void testPhoneTooLong() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("111", "First", "Last", "12345678901", "222 A St");
            });
        }

        @Test
        void testPhoneWithNonDigits() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("111", "First", "Last", "12345abcde", "222 A St");
            });
        }

        @Test
        void testPhoneExactlyTenDigits() {
            // Should not throw exception
            Contact contact = new Contact("111", "First", "Last", "1234567890", "222 A St");
            assertEquals("1234567890", contact.getPhone());
        }
    }

    @Nested
    class InvalidAddressTests {
        @Test
        void testAddressNull() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("111", "First", "Last", "1234567890", null);
            });
        }

        @Test
        void testAddressTooLong() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Contact("111", "First", "Last", "1234567890", 
                           "222 An Address That Is Way Too Long For This Field");
            });
        }

        @Test
        void testAddressExactlyMaxLength() {
            // Should not throw exception - 30 characters exactly
            String maxLengthAddress = "123 Main Street, Apt 4B";
            Contact contact = new Contact("111", "First", "Last", "1234567890", maxLengthAddress);
            assertEquals(maxLengthAddress, contact.getAddress());
        }
    }

    @Test
    void testAllFieldsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, null, null, null, null);
        });
    }
}