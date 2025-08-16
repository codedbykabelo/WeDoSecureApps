package za.ac.tut.message;

public class Message {

    private int shift;

    public Message(int shift) {
        this.shift = shift;
    }

    public String encrypt(String input) {
        String encrypted = "";

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (Character.isUpperCase(c)) {
                encrypted += (char) (((c - 'A' + shift) % 26) + 'A');
            } else if (Character.isLowerCase(c)) {
                encrypted += (char) (((c - 'a' + shift) % 26) + 'a');
            } else {

                encrypted += c;
            }
        }

        return encrypted;

    }

}
