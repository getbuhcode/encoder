public class Main {
    public static void main(String[] args) {

        /*
        * About this application:
        *
        * This application is case-sensitive,
        * lower case is not supported (as per reference table provided).
        *
        * If lower case is required,
        * need to uncomment the reference string in the MessageEncoder class.
        *
        * If Offset is not defined, it will be generated randomly.
        */

        // Instantiate an encoder object
        MessageEncoder encoder = new MessageEncoder();

        String plainText = "";
        String encodedText = "";
        String decodedText = "";

        /* TEST CASES */

        /*
        Scenario 1: OFFSET CHARACTER IS THE CHARACTER TO BE DECODED.
        OFFSET NEEDS TO BE DEFINED EXPLICITLY WHEN CALLING THE encode(plaintext, offset) METHOD
        SINCE B HAS AN INDEX OF 1 IN THE REFERENCE TABLE PROVIDED, OFFSET IS 1.
        */
        plainText = "BBBBB";
        System.out.println("PlainText Before Encoding: " + plainText);

        encodedText = encoder.encode(plainText, 1);
        decodedText = encoder.decode(encodedText);

        System.out.println("Encoded Text: " + encodedText);
        System.out.println("PlainText After Decoding: " + decodedText + "\n");

        /*
        Scenario 2: OFFSET CHARACTER "B" IS AFTER THE CHARACTER "A" TO BE DECODED.
        USING B AS THE OFFSET WITH AN INDEX OF 1 IN THE REFERENCE TABLE PROVIDED, OFFSET IS 1.
        */
        plainText = "AAAAA";
        System.out.println("PlainText Before Encoding: " + plainText);

        encodedText = encoder.encode(plainText, 1);
        decodedText = encoder.decode(encodedText);

        System.out.println("Encoded Text: " + encodedText);
        System.out.println("PlainText After Decoding: " + decodedText + "\n");

        /*
        Scenario 3: OFFSET CHARACTER "B" IS BEFORE THE CHARACTER "C" TO BE DECODED.
        USING B AS THE OFFSET WITH AN INDEX OF 1 IN THE REFERENCE TABLE PROVIDED, OFFSET IS 1.
        */
        plainText = "CCCCC";
        System.out.println("PlainText Before Encoding: " + plainText);

        encodedText = encoder.encode(plainText, 1);
        decodedText = encoder.decode(encodedText);

        System.out.println("Encoded Text: " + encodedText);
        System.out.println("PlainText After Decoding: " + decodedText + "\n");

        /* TEST SAMPLE 1, USING ENCODE METHOD BY GETTING RANDOM OFFSET GENERATED */
        plainText = "This is the BEST WORLD IN PROGRAMMING ()0987654321?";
        System.out.println("PlainText Before Encoding: " + plainText);

        encodedText = encoder.encode(plainText);
        decodedText = encoder.decode(encodedText);

        System.out.println("Encoded Text: " + encodedText);
        System.out.println("PlainText After Decoding: " + decodedText + "\n");

        /* TEST SAMPLE 2, USING ENCODE METHOD BY EXPLICITLY DEFINING OFFSET */
        plainText = "THIS PROGRAMME HELPS TO ENCODE AND DECODE YOUR MESSAGE 0123456789";
        System.out.println("PlainText Before Encoding: " + plainText);

        encodedText = encoder.encode(plainText,10);
        decodedText = encoder.decode(encodedText);

        System.out.println("Encoded Text: " + encodedText);
        System.out.println("PlainText After Decoding: " + decodedText);

    }
}