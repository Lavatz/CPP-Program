package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import contacts.Contact;
import contacts.ContactService;

class ContactServiceTest {
    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
    }

    @Nested
    class AddContactTests {
        @Test
        void testAddContact() {
            Contact contact = new Contact("111", "First", "Last", "1234567890", "222 A St");
            service.addContact(contact);
            Contact retrieved = service.getContact("111");
            
            assertAll("Add contact",
                () -> assertNotNull(retrieved),
                () -> assertEquals("111", retrieved.getContactId()),
                () -> assertEquals("First", retrieved.getFirstName()),
                () -> assertEquals("Last", retrieved.getLastName()),
                () -> assertEquals("1234567890", retrieved.getPhone()),
                () -> assertEquals("222 A St", retrieved.getAddress())
            );
        }

        @Test
        void testAddMultipleContacts() {
            Contact contact1 = new Contact("111", "First1", "Last1", "1234567890", "222 A St");
            Contact contact2 = new Contact("222", "First2", "Last2", "0987654321", "333 B St");
            
            service.addContact(contact1);
            service.addContact(contact2);
            
            assertAll("Multiple contacts added",
                () -> assertNotNull(service.getContact("111")),
                () -> assertNotNull(service.getContact("222")),
                () -> assertEquals("First1", service.getContact("111").getFirstName()),
                () -> assertEquals("First2", service.getContact("222").getFirstName())
            );
        }

        @Test
        void testAddDuplicateContact() {
            Contact contact1 = new Contact("111", "First", "Last", "1234567890", "222 A St");
            Contact contact2 = new Contact("111", "Second", "Third", "0987654321", "444 B St");
            service.addContact(contact1);
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                service.addContact(contact2);
            });
            
            assertEquals("Contact ID already exists.", exception.getMessage());
        }

        @Test
        void testAddNullContact() {
            assertThrows(NullPointerException.class, () -> {
                service.addContact(null);
            });
        }
    }

    @Nested
    class DeleteContactTests {
        @Test
        void testDeleteContact() {
            Contact contact = new Contact("111", "First", "Last", "1234567890", "222 A St");
            service.addContact(contact);
            service.deleteContact("111");
            assertNull(service.getContact("111"));
        }

        @Test
        void testDeleteNonExistentContact() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                service.deleteContact("999");
            });
            
            assertEquals("Contact ID does not exist.", exception.getMessage());
        }

        @Test
        void testDeleteContactWithNullId() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                service.deleteContact(null);
            });
            
            assertEquals("Contact ID does not exist.", exception.getMessage());
        }

        @Test
        void testDeleteThenAddSameId() {
            Contact contact = new Contact("111", "First", "Last", "1234567890", "222 A St");
            service.addContact(contact);
            service.deleteContact("111");
            
            // Should be able to add contact with same ID after deletion
            Contact newContact = new Contact("111", "NewFirst", "NewLast", "1111111111", "New Address");
            service.addContact(newContact);
            
            assertNotNull(service.getContact("111"));
            assertEquals("NewFirst", service.getContact("111").getFirstName());
        }
    }

    @Nested
    class UpdateContactTests {
        private Contact contact;

        @BeforeEach
        void setUp() {
            contact = new Contact("111", "First", "Last", "1234567890", "222 A St");
            service.addContact(contact);
        }

        @Test
        void testUpdateContactFirstName() {
            service.updateContact("111", "Second", null, null, null);
            Contact updated = service.getContact("111");
            
            assertAll("Update first name only",
                () -> assertEquals("Second", updated.getFirstName()),
                () -> assertEquals("Last", updated.getLastName()),
                () -> assertEquals("1234567890", updated.getPhone()),
                () -> assertEquals("222 A St", updated.getAddress())
            );
        }

        @Test
        void testUpdateContactLastName() {
            service.updateContact("111", null, "NewLast", null, null);
            Contact updated = service.getContact("111");
            
            assertAll("Update last name only",
                () -> assertEquals("First", updated.getFirstName()),
                () -> assertEquals("NewLast", updated.getLastName()),
                () -> assertEquals("1234567890", updated.getPhone()),
                () -> assertEquals("222 A St", updated.getAddress())
            );
        }

        @Test
        void testUpdateContactPhone() {
            service.updateContact("111", null, null, "0987654321", null);
            Contact updated = service.getContact("111");
            
            assertAll("Update phone only",
                () -> assertEquals("First", updated.getFirstName()),
                () -> assertEquals("Last", updated.getLastName()),
                () -> assertEquals("0987654321", updated.getPhone()),
                () -> assertEquals("222 A St", updated.getAddress())
            );
        }

        @Test
        void testUpdateContactAddress() {
            service.updateContact("111", null, null, null, "444 B St");
            Contact updated = service.getContact("111");
            
            assertAll("Update address only",
                () -> assertEquals("First", updated.getFirstName()),
                () -> assertEquals("Last", updated.getLastName()),
                () -> assertEquals("1234567890", updated.getPhone()),
                () -> assertEquals("444 B St", updated.getAddress())
            );
        }

        @Test
        void testUpdateContactAllFields() {
            service.updateContact("111", "NewFirst", "NewLast", "1111111111", "New Address");
            Contact updated = service.getContact("111");
            
            assertAll("Update all fields",
                () -> assertEquals("NewFirst", updated.getFirstName()),
                () -> assertEquals("NewLast", updated.getLastName()),
                () -> assertEquals("1111111111", updated.getPhone()),
                () -> assertEquals("New Address", updated.getAddress())
            );
        }

        @Test
        void testUpdateContactWithInvalidId() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                service.updateContact("999", "Second", null, null, "444 B St");
            });
            
            assertEquals("Contact ID does not exist.", exception.getMessage());
        }

        @Test
        void testUpdateContactWithNullId() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                service.updateContact(null, "Second", null, null, "444 B St");
            });
            
            assertEquals("Contact ID does not exist.", exception.getMessage());
        }

        @Test
        void testUpdateContactWithInvalidData() {
            // Try to update with invalid phone number (too short)
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                service.updateContact("111", null, null, "12345", null);
            });
            
            // Verify the exception message
            assertTrue(exception.getMessage().contains("Invalid phone"));
            
            // Verify original contact is unchanged
            Contact original = service.getContact("111");
            assertAll("Original contact unchanged after failed update",
                () -> assertEquals("First", original.getFirstName()),
                () -> assertEquals("Last", original.getLastName()),
                () -> assertEquals("1234567890", original.getPhone()),
                () -> assertEquals("222 A St", original.getAddress())
            );
        }

        @Test
        void testUpdateContactWithAllNullParameters() {
            // All update parameters are null - should keep original values
            service.updateContact("111", null, null, null, null);
            Contact updated = service.getContact("111");
            
            assertAll("No changes when all parameters are null",
                () -> assertEquals("First", updated.getFirstName()),
                () -> assertEquals("Last", updated.getLastName()),
                () -> assertEquals("1234567890", updated.getPhone()),
                () -> assertEquals("222 A St", updated.getAddress())
            );
        }
    }

    @Nested
    class GetContactTests {
        @Test
        void testGetNonExistentContact() {
            assertNull(service.getContact("999"));
        }

        @Test
        void testGetContactWithNullId() {
            assertNull(service.getContact(null));
        }

        @Test
        void testGetContactAfterMultipleOperations() {
            Contact contact1 = new Contact("111", "First1", "Last1", "1234567890", "222 A St");
            Contact contact2 = new Contact("222", "First2", "Last2", "0987654321", "333 B St");
            
            service.addContact(contact1);
            service.addContact(contact2);
            service.deleteContact("111");
            service.updateContact("222", "Updated", null, null, null);
            
            Contact result = service.getContact("222");
            
            assertAll("Contact after multiple operations",
                () -> assertNotNull(result),
                () -> assertEquals("Updated", result.getFirstName()),
                () -> assertEquals("Last2", result.getLastName()),
                () -> assertNull(service.getContact("111"))
            );
        }
    }
}