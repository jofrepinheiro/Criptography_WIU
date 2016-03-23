
// Program written by Binto George for CS395 Sudents
// This program sends Alice sends an encrypted message to Bob

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.Cipher;

public class RSA {
  public static void main(String[] unused) throws Exception {
    // Generate key pair
    String cryptSpec = "RSA"; //using RSA
    KeyPairGenerator bobKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
    bobKeyPairGenerator.initialize(1024);
    KeyPair bobKeyPair = bobKeyPairGenerator.generateKeyPair();
    
    KeyPairGenerator aliceKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
    aliceKeyPairGenerator.initialize(1024);
    KeyPair aliceKeyPair = aliceKeyPairGenerator.generateKeyPair();
    
    Signature aliceSignature = Signature.getInstance("SHA1withRSA");
    
    // Alice's end (Sender)
    
    PublicKey bobPublicKey = bobKeyPair.getPublic();
    PrivateKey alicePrivateKey = aliceKeyPair.getPrivate();
    
    byte[] m = "hi!!!!!!".getBytes();
    Cipher AliceEncryptionBox = Cipher.getInstance(cryptSpec);
    aliceSignature.initSign(alicePrivateKey);
    AliceEncryptionBox.init(Cipher.ENCRYPT_MODE, bobPublicKey);
  
    byte[] c = AliceEncryptionBox.doFinal(m);
    
    String cipher = new String(c);
    System.out.println("Ciphertext: " + cipher);
   
    
    //Bob's end (Receiver)
    PublicKey alicePublicKey = aliceKeyPair.getPublic();
    PrivateKey bobPrivateKey = bobKeyPair.getPrivate();    
    Cipher BobDecryptionBox = Cipher.getInstance(cryptSpec);
    BobDecryptionBox.init(Cipher.DECRYPT_MODE, bobPrivateKey);
    
    aliceSignature.initVerify(alicePublicKey);    
    m=BobDecryptionBox.doFinal(c);

    //converting bytes to string -- so that it can be printed
    String s = new String(m);
    System.out.println(s);
 }
}
