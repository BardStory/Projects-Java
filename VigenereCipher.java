//Author Joshua Bard
//UVIC # V00865924
//CSC115 Assignment 1

//Class VigenereCipher that implements the Cipher Class
public class VigenereCipher implements Cipher 
{
    //Initiates the key
    private String aKey;   
    //new method VigenereCipher
    public VigenereCipher(String key)
    {
        aKey = key;
    }
    /*
    Converts the string to an int array
    Returns an integer array created from the passed in.
    */
    //Converts a string to an int array meant for lower case letters
    private int[] stringToIntArray(String text)
    { 
        int[] arrayFromString = new int[text.length()]; //create new array

        for (int k = 0; k < text.length(); k++)
        {   //
            arrayFromString[k] = text.charAt(k) - 97; 
            /*CHECK ASCII TABLE for the list of lowercase letters
            https://commons.wikimedia.org/wiki/File:ASCII-Table-wide.svg
            */
        }

        return arrayFromString; //returns
    } 
    //Converts the intArray to a string to be returned
    private String intArrayToString(int[] encodedText)
    {
        char[] charArray = new char[encodedText.length]; 

        for (int k = 0; k < encodedText.length; k++)
        {
            charArray[k] = (char) (encodedText[k] + 97); //converts integer array to string
            //CHECK ASCII TABLE for the list of lowercase letters
        }
        String encodedString = new String(charArray);

        return encodedString;
    }
    /*
     Prints the text and the array of elements
     if their are more than one array, it will print with commas
     Prints the passed in text followed by the elements of the array
     */
    //creates the method dumpArray
    private void dumpArray(int[] array, String text)
    {
        int k = 0;
        System.out.print(text);
        System.out.print(" "); 
    
        for (k = 0; k < array.length; k++)
        { 
            if (k > 0)
            { 
                System.out.print(", ");
            }
            System.out.print(array[k]);
        }
        System.out.println();
    }
    //method descriptions for encrypt, decrypt and setKey from Cipher.java
    /**
     * Encrypts passed in plaintext using a key.
     * @param plaintext The text to be encrypted.
     * @return The encrypted plaintext.
     */
    //from the cipher class
    public String encrypt(String plaintext)
    { //encrypts the  plaintext using the key
        
        //variables
        int k = 0;
        int j = 0;
        int i = 0;
        StringBuilder s = new StringBuilder(); 
        /*Stringbuilder info 
        https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
        */
        for (k = 0; k < plaintext.length(); k++)
        {
            s.append(aKey.charAt(j));
            j++;
            if (j > (aKey.length() - 1))
            {
                j = 0;
            }
        }
        String expandedKey = s.toString();

        int[] inputArray = stringToIntArray(plaintext);
        int[] keyArray = stringToIntArray(expandedKey);

        //combines the arrays and 
        int[] combinedArray = new int[plaintext.length()];
        
        for (i = 0; i < plaintext.length(); i++)
        {
            combinedArray[i] = ((inputArray[i] + keyArray[i]) % 26); //modulo to encrypt
        }

        //converts to a string so it can be returned
        String encryptedString = intArrayToString(combinedArray);

        return encryptedString;
    }
    
    /**
     * Decrypts passed in ciphertext using a key.
     * @param ciphertext The text to be decrypted.
     * @return The decrypted ciphertext.
     */
    
    public String decrypt(String ciphertext)
    {
        //this will be similar process to encrypth
        int j = 0;
        int k = 0;
        int i = 0;
        StringBuilder s = new StringBuilder();

        for (k = 0; k < ciphertext.length(); k++)
        { //make sure to use cipher text and not plantext
            
            s.append(aKey.charAt(j));
            j++;
            
            if (j > (aKey.length() - 1))
            {
                j = 0;
            }
        }

        String expandedKey = s.toString();
        
        //same code as above to make strings arrays
        int[] inputArray = stringToIntArray(ciphertext);
        int[] keyArray = stringToIntArray(expandedKey);
        

        int[] combinedArray = new int[ciphertext.length()];
        
        for (i = 0; i < ciphertext.length(); i++)
        {
            combinedArray[i] = ((26 + inputArray[i] - keyArray[i]) % 26);
        }
        
        String decryptedString = intArrayToString(combinedArray);
        
        return decryptedString;
    }
    
    /**
     * Sets the key to be used by the Cipher, especially if this needs
     * to change during the life of a Cipher object (i.e., the
     * constructor already establishes the key when a Cipher object
     * is first created).
     * @param key A plain text key.
     */

    public void setKey(String key)
    {
        aKey = key;
    }


    /*
    The main method in this class is to used to test the code
    *The following contributes a possible four different test 
    *Each of which will either return a pass or fail for each test to check if it works
    */
    public static void main(String args[])
    {
        //testers are the same as the txt files
        VigenereCipher cipher = new VigenereCipher("bob");

        
        //The first test
        int k = 0;

        int[] expectedArray = {19, 7, 4, 12, 4, 18, 18, 0, 6, 4};
        int[] realArray = cipher.stringToIntArray("themessage");
       
        for (k = 0; k < expectedArray.length; k++)
        {
            if (cipher.stringToIntArray("themessage")[k] != expectedArray[k])
            {
                System.out.println("Test #1: stringToIntArray - failed");
                System.exit(1);
            }
        }
        
        System.out.println("Test #1: stringToIntArray - passed");
        
        
        //The second test
        /*
        int[] inputArray = {19, 7, 4, 12, 4, 18, 18, 0, 6, 4};
        String expectedString = "themessage";
       
        if (cipher.intArrayToString(inputArray).equals(expectedString))
        {
            System.out.println("Test #2: intArrayToString - passed");
        } 
        
        else 
        {
            System.out.println("Test #2: intArrayToString - failed");
        }
        */

    
        //The third test
        
        String encryptInput = "themessage";
        String expectedEncryptOutput = "uvfnsttohf";

        if (cipher.encrypt(encryptInput).equals(expectedEncryptOutput))
        {
            System.out.println("Test #3 Encrypt: encrypt - passed");
        } 
        
        else 
        {
            System.out.println("Test #3 Encrypt: encrypt - failed");
        }
        


        //The Fourth Test which should be used with the third test
        
        String encryptedInput = "uvfnsttohf";
        String expectedDecryptOutput = "themessage";
        
        if (cipher.decrypt(encryptedInput).equals(expectedDecryptOutput))
        {
            System.out.println("Test #4 Decrypt: decrypt - passed");
        }
        
        else 
        {
            System.out.println("Test #4 Decrypt: decrypt - failed");
        }

        
    }
    
}