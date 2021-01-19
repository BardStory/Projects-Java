public class CipherTester {

    public static void main(String args[]){
        VigenereCipher cipher = new VigenereCipher("bob");

        
        //test 1
        int[] expectedArray = {19, 7, 4, 12, 4, 18, 18, 0, 6, 4};
        int[] realArray = cipher.stringToIntArray("themessage");
        for (int k = 0; k < expectedArray.length; k++){
            if (cipher.stringToIntArray("themessage")[k] != expectedArray[k]){
                System.out.println("Test 1: stringToIntArray - FAILED");
                System.exit(1);
            }
        }
        System.out.println("Test 1: stringToIntArray - PASSED");
        //test 2
        int[] inputArray = {19, 7, 4, 12, 4, 18, 18, 0, 6, 4};
        String expectedString = "themessage";
        if (cipher.intArrayToString(inputArray).equals(expectedString)){
            System.out.println("Test 2: intArrayToString - PASSED");
        } else {
            System.out.println("Test 2: intArrayToString - FAILED");
        }
        

        /*
        //test 3
        String encryptInput = "themessage";
        String expectedEncryptOutput = "uvfnsttohf";

        if (cipher.encrypt(encryptInput).equals(expectedEncryptOutput)){
            System.out.println("Test Encrypt: encrypt - PASSED");
        } else {
            System.out.println("Test Encrypt: encrypt - FAILED");
        }

        //test 4
        String encryptedInput = "uvfnsttohf";
        String expectedDecryptOutput = "themessage";
        
        if (cipher.decrypt(encryptedInput).equals(expectedDecryptOutput)){
            System.out.println("Test Decrypt: decrypt - PASSED");
        } else {
            System.out.println("Test Decrypt: decrypt - FAILED");
        }

        */
    }
}