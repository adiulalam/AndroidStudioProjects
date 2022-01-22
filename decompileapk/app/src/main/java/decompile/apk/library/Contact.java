package decompile.apk.library;

public class Contact {
    String _email;
    int _id;
    String _name;
    String _phone_number;

    public Contact(int i, String str, String str2) {
        this._id = i;
        this._name = str;
        this._phone_number = str2;
    }

    public Contact(String str, String str2) {
        this._name = str;
        this._phone_number = str2;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int i) {
        this._id = i;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String str) {
        this._name = str;
    }

    public String getPhoneNumber() {
        return this._phone_number;
    }

    public void setPhoneNumber(String str) {
        this._phone_number = str;
    }

    public String getEmail() {
        return this._email;
    }

    public void setEmail(String str) {
        this._email = str;
    }
}
