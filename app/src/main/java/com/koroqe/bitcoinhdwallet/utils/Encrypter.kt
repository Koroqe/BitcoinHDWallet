package com.koroqe.bitcoinhdwallet.utils

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidParameterSpecException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.SecretKeySpec

/**
 * Created by danielyakovlev on 7/14/17.
 */

object Encrypter {
    private val cipherTransformation = "AES/ECB/PKCS5Padding"
    val DEFAULT_CIPHER_KEY = "9nf32y48NB&2m3bi3HBIfquyevciuhec"

    @Throws(NoSuchAlgorithmException::class, NoSuchPaddingException::class, InvalidKeyException::class,
            InvalidParameterSpecException::class, IllegalBlockSizeException::class,
            BadPaddingException::class, UnsupportedEncodingException::class)
    fun encryptString(message: String, key: String): String {
        val validKey: String = buildValidKey(key)

        val secretKey = SecretKeySpec(validKey.toByteArray(), "AES")
        val cipher: Cipher? = Cipher.getInstance(cipherTransformation)
        cipher!!.init(Cipher.ENCRYPT_MODE, secretKey)
        val cipherTextByteArray = cipher.doFinal(message.toByteArray(charset("UTF-8")))
        return Base64.encodeToString(cipherTextByteArray, Base64.DEFAULT)
    }

    @Throws(NoSuchPaddingException::class, NoSuchAlgorithmException::class,
            InvalidParameterSpecException::class, InvalidAlgorithmParameterException::class,
            InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class,
            UnsupportedEncodingException::class)
    fun decryptString(cipherText: String, key: String): String {
        val validKey: String = buildValidKey(key)
        val secretKey = SecretKeySpec(validKey.toByteArray(), "AES")
        val cipher: Cipher? = Cipher.getInstance(cipherTransformation)
        cipher!!.init(Cipher.DECRYPT_MODE, secretKey)
        val encryptedTextFromBase64 = Base64.decode(cipherText, Base64.DEFAULT)
        val decryptString = String(cipher.doFinal(encryptedTextFromBase64), charset("UTF-8"))
        return decryptString
    }

    private fun buildValidKey(key: String) =
        if (key.length > 32)
            key.substring(0, 32)
        else if(key.length == 32 || key.length == 16)
            key
        else
            ""

}