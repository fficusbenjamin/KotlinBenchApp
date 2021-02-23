package com.example.kotlinpj.Benchmarks

class FannkuchTest {
    fun fannkuch(n: Int): Int {
        val perm = IntArray(n)
        val perm1 = IntArray(n)
        val count = IntArray(n)
        var maxFlipsCount = 0
        var permCount = 0
        var checksum = 0
        for (i in 0 until n) perm1[i] = i
        var r = n
        while (true) {
            while (r != 1) {
                count[r - 1] = r
                r--
            }
            for (i in 0 until n) perm[i] = perm1[i]
            var flipsCount = 0
            var k: Int
            while (perm[0].also { k = it } != 0) {
                val k2 = k + 1 shr 1
                for (i in 0 until k2) {
                    val temp = perm[i]
                    perm[i] = perm[k - i]
                    perm[k - i] = temp
                }
                flipsCount++
            }
            maxFlipsCount = Math.max(maxFlipsCount, flipsCount)
            checksum += if (permCount % 2 == 0) flipsCount else -flipsCount

            // Use incremental change to generate another permutation
            while (true) {
                if (r == n) {
                    println(checksum)
                    return maxFlipsCount
                }
                val perm0 = perm1[0]
                var i = 0
                while (i < r) {
                    val j = i + 1
                    perm1[i] = perm1[j]
                    i = j
                }
                perm1[r] = perm0
                count[r] = count[r] - 1
                if (count[r] > 0) break
                r++
            }
            permCount++
        }
    }

    fun runBenchmark() {
        val start_time = System.currentTimeMillis()
        val n = 7
        //if (args.length > 0) n = Integer.parseInt(args[0]);
        println("Pfannkuchen(" + n + ") = " + fannkuch(n))
        time_diff = System.currentTimeMillis() - start_time
    }

    companion object {
        var time_diff: Long = 0
        fun results(): String {
            return String.format("Time for the Fannkuch test: %f s\n", time_diff / 1e3)
        }
    }
}