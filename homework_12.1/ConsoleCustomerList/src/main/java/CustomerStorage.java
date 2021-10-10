import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws IllegalEmailException, IllegalPhoneException, IllegalNameException {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if(components.length != 4) {
            throw new ArrayIndexOutOfBoundsException("Wrong correct command!");
        }
        if(!components[INDEX_EMAIL].matches("\\w+@\\w+.\\w+$")) {
            throw new IllegalEmailException("Wrong email format.");
        }
        if(!components[INDEX_PHONE].matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")) {
            throw new IllegalPhoneException("Wrong phone format.");
        }
        if(components[INDEX_NAME].matches("^.*[^A-zА-яЁё].*$") || components[INDEX_SURNAME].matches("^.*[^A-zА-яЁё].*$")) {
            throw new IllegalNameException("Wrong name/surname format");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
    protected static class IllegalEmailException extends Throwable{
        protected IllegalEmailException(String email) {
            super(email);
        }
    }
    protected static class IllegalPhoneException extends Throwable {
        protected IllegalPhoneException(String phone) {
            super(phone);
        }
    }
    protected static class IllegalNameException extends Throwable {
        protected IllegalNameException(String name) {
            super(name);
        }
    }
}