package com.example.kotlinpj.Benchmarks

import java.util.Base64

object Base64Test {

    const val STR_SIZE = 131072
    const val TRIES = 8192

    val dec = Base64.getDecoder()
    val enc = Base64.getEncoder()

    var s_encoded = 0
    var t_encoded = 0.0
    var t_decoded = 0.0

    /*var str = ByteArray(0)
    var str2: String? = null
    var s_encoded = 0
    var t_encoded = 0.0
    var t_decoded = 0.0


    fun main() {
        for (fixture in arrayOf(arrayOf("hello", "aGVsbG8="), arrayOf("world", "d29ybGQ="))) {
            val src = fixture[0]
            val dst = fixture[1]
            val encoded = enc.encodeToString(src.toByteArray())
            if (encoded != dst) {
                System.err.printf("%s != %s\n", encoded, dst)
                System.exit(1)
            }
            val decoded = String(dec.decode(dst))
            if (decoded != src) {
                System.err.printf("%s != %s\n", decoded, src)
                System.exit(1)
            }
        }
        str = ByteArray(STR_SIZE)
        Arrays.fill(str, 'a'.toByte())
        str2 = enc.encodeToString(str)
        val encoded = str2.toByteArray()
        val b_arr = dec.decode(encoded)
        s_encoded = 0
        val t = System.nanoTime()
        for (i in 0 until TRIES) {
            s_encoded += enc.encodeToString(str).length
        }
        t_encoded = (System.nanoTime() - t) / 1e9
        var s_decoded = 0
        val t1 = System.nanoTime()
        for (i in 0 until TRIES) {
            s_decoded += dec.decode(encoded).size
        }
        t_decoded = (System.nanoTime() - t1) / 1e9
        println(String.format("encode %s... to %s...: %d, %.2f", String(Arrays.copyOf(str, 4)), str2.substring(0, 4), s_encoded, t_encoded))
        println(String.format("decode %s... to %s...: %d, %.2f", str2.substring(0, 4), String(Arrays.copyOf(b_arr, 4)), s_decoded, t_decoded))
        println(String.format("overall time for the encoding and deconding of Base64 test: " + (t_encoded + t_decoded) + "s"))
    }*/

    fun main() {
        for ((src, dst) in arrayOf(
            arrayOf("hello", "aGVsbG8="), arrayOf("world", "d29ybGQ=")
        )) {
            val encoded = enc.encodeToString(src.toByteArray())
            if (encoded != dst) {
                System.err.println("${encoded} != ${dst}")
                System.exit(1)
            }
            val decoded = String(dec.decode(dst))
            if (decoded != src) {
                System.err.println("${decoded} != ${src}")
                System.exit(1)
            }
        }

        val str = "a".repeat(STR_SIZE).toByteArray()
        val encStr = enc.encodeToString(str)
        val decBytes = dec.decode(encStr)


        val t1 = System.nanoTime()
        repeat(TRIES) {
            s_encoded += enc.encodeToString(str).length
        }
        t_encoded = (System.nanoTime() - t1) / 1e9

        var s_decoded = 0
        val t2 = System.nanoTime()
        repeat(TRIES) {
            s_decoded += dec.decode(encStr).size
        }
        t_decoded = (System.nanoTime() - t2) / 1e9



        println(
            "encode ${String(str.sliceArray(1..4))}... " +
                    "to ${encStr.substring(0, 4)}...: ${s_encoded}, ${t_encoded}"
        )
        println(
            "decode ${encStr.substring(0, 4)}... to " +
                    "${String(decBytes.sliceArray(1..4))}...: " +
                    "${s_decoded}, ${t_decoded}"
        )
        println("overall time: ${t_encoded + t_decoded}s")
    }



    fun results(): String {
        return String.format("overall time for Base64 test: " + (t_encoded + t_decoded) + "s")
    }
}