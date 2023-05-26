import java.util.TreeMap;

public class MessageEncoder implements Encoder {

    private final String referenceStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

//    Uncomment the line below for encoding lower case messages
//    private final String referenceStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789()*+,-./";

    private int offsetValue;

    @Override
    public String encode(String plainText) {
        return encoder(plainText, -1);
    }

    public String encode(String plainText, int offset) {
        offsetValue = offset;
        return encoder(plainText, offset);
    }

    @Override
    public String decode(String encodedText) {
        return decoder(encodedText);
    }

    /* encoder Method */
    // Encode the plain text based on the reference string/table
    private String encoder (String plainText, int offset) {

        // Use TreeMap as it is BST, so search operation will be O(logn)
        TreeMap<String, Integer> referenceTable = getReferenceTable();

        String offsetChar = "";
        String encodedMsg = "";
        String encodeChar = "";

        // if offset is not provided
        if (offset == -1) {

            // Get a random offset character based on the reference string/table
            offsetChar = generateOffset();

            System.out.println("Offset Character: " + offsetChar);

            // Assign the first character of the encoded message as the offset character
            encodedMsg = offsetChar;

            // Get the offset integer of the random character
            offsetValue = referenceTable.get(offsetChar);
        }
        else {

            // If offset is provided, get the offset character from reference string/table
            offsetChar = String.valueOf(referenceStr.charAt(offset));

            System.out.println("Offset Character: " + offsetChar);

            // Assign the first character of the encoded message as the offset character
            encodedMsg = offsetChar;
        }


        // Encode the plaintext according to the reference table
        for (int i = 0; i < plainText.length(); i++) {

            // Get the character to be encoded
            String charToEncode = String.valueOf(plainText.charAt(i));

            // if charToEncode exists in reference table, encode the character
            if (referenceTable.containsKey(charToEncode)) {
                int encodeIndex = getEncodingIndex(referenceTable.get(charToEncode), offsetValue);
                encodeChar = String.valueOf(referenceStr.charAt(encodeIndex));
                encodedMsg += encodeChar;
            }
            else {
                // if charToEncode is not in the reference table, character will be itself
                encodedMsg += charToEncode;
            }
        }

        return encodedMsg;
    }

    /* decoder Method */
    // Decode the encoded text based on the reference string/table
    private String decoder(String encodedText) {

        // Get the offset character
        String offsetChar = encodedText.substring(0,1);

        //Get the message to be decoded
        String decodeMsg = encodedText.substring(1);

        // Use TreeMap as it is BST, so search operation will be O(logn)
        TreeMap<String, Integer> referenceTable = getReferenceTable();
        offsetValue = referenceTable.get(offsetChar);

        String decodedMsg = "";
        String decodeChar = "";

        for (int i = 0; i < decodeMsg.length(); i++) {

            String charToDecode = String.valueOf(decodeMsg.charAt(i));

            // if charToDecode exists in reference table, decode the character
            if (referenceTable.containsKey(charToDecode)) {
                int decodeIndex = getDecodingIndex(referenceTable.get(charToDecode), offsetValue);
                decodeChar = String.valueOf(referenceStr.charAt(decodeIndex));
                decodedMsg += decodeChar;
            }
            else {
                // if charToDecode is not in the reference table, character will be itself
                decodedMsg += charToDecode;
            }
        }

        return decodedMsg;
    }

    /* getReferenceTable Method */
    // Get the reference table in a TreeMap form
    private TreeMap<String, Integer> getReferenceTable () {
        TreeMap<String,Integer> tm = new TreeMap<>();
        for (int i = 0; i < referenceStr.length(); i++) {
            String referenceChar = String.valueOf(referenceStr.charAt(i));
            tm.put(referenceChar, i);
        }
        return tm;
    }

    /* offset Method */
    // Get random offset char that is included in the reference table
    private String generateOffset() {

        /*
        Since offset character should not be the same as reference string/table,
        else the plain text will not be encoded so need to remove first character
        from reference string/table
        */
        String offsetStr = referenceStr.substring(1);

        StringBuilder s = new StringBuilder(1);
        int offsetIndex = (int)(offsetStr.length() * Math.random());
        s.append(offsetStr.charAt(offsetIndex));
        String offsetChar = s.toString();
        return offsetChar;
    }

    /* getIndex Method */
    // Get the index of the character to be used for encoding
    private int getEncodingIndex(int encodeIndex, int offset) {

        int index = 0;
        int diff = encodeIndex - offset;

        // if the character to be encoded is not offset character
        if (diff != 0) {

            // if character to be encoded is before offset character,
            // add length of reference string/table
            if (diff < 0) {
                index = diff + referenceStr.length();
            }
            else {
                // if character to be encoded is after offset character,
                // the index will remain as it is
                index = diff;
            }
        }
        return index;
    }

    /* getIndex Method */
    // Get the index of the character to be used for decoding
    private int getDecodingIndex (int decodeIndex, int offset) {

        int index = 0;
        int diff = referenceStr.length() - decodeIndex;

        if (diff != 0) {
            // if character to be decoded is before offset character,
            // minimise length of reference string/table plus offset
            if (diff < offset) {
                index = decodeIndex - referenceStr.length() + offset;
            }
            else if (diff > offset) {
                index = decodeIndex + offset;
            }
        }

        return index;
    }
}
