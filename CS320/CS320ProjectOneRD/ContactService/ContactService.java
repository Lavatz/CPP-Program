package contacts;

import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Contact ID already exists.");
        }
        contacts.put(contact.getContactId(), contact);
    }

    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID does not exist.");
        }
        contacts.remove(contactId);
    }

    public void updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        Contact existingContact = contacts.get(contactId);
        if (existingContact == null) {
            throw new IllegalArgumentException("Contact ID does not exist.");
        }
        
        //Use existing values if new ones are null
        String newFirstName = (firstName != null) ? firstName : existingContact.getFirstName();
        String newLastName = (lastName != null) ? lastName : existingContact.getLastName();
        String newPhone = (phone != null) ? phone : existingContact.getPhone();
        String newAddress = (address != null) ? address : existingContact.getAddress();
        
        
        Contact updatedContact = new Contact(
            contactId, 
            newFirstName, 
            newLastName, 
            newPhone, 
            newAddress
        );
        
        
        contacts.put(contactId, updatedContact);
    }
    
    
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }
}