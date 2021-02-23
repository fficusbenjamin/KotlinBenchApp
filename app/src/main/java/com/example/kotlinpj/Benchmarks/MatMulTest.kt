package com.example.kotlinpj.Benchmarks

object MatMulTest {
    var time_diff: Long = 0




    private fun matGen(n: Int, seed: Double): Array<DoubleArray> {
        val a = Array(n) { DoubleArray(n) }
        val tmp = seed / n.toDouble() / n.toDouble()
        for (i in 0 until n) {
            for (j in 0 until n) {
                a[i][j] = tmp * (i - j).toDouble() * (i + j).toDouble()
            }
        }
        return a
    }

    private fun matMul(a: Array<DoubleArray>, b: Array<DoubleArray>): Array<DoubleArray> {
        val m = a.size
        val n = a[0].size
        val p = b[0].size
        val x = Array(m) { DoubleArray(p) }
        val c = Array(p) { DoubleArray(n) }
        for (i in 0 until n)  // transpose
            for (j in 0 until p) c[j][i] = b[i][j]
        for (i in 0 until m) for (j in 0 until p) {
            var s = 0.0
            for (k in 0 until n) s += a[i][k] * c[j][k]
            x[i][j] = s
        }
        return x
    }

    private fun calc(n: Int): Double {
        val size = n / 2 * 2
        val a = matGen(size, 1.0)
        val b = matGen(size, 2.0)
        val x = matMul(a, b)
        return x[size / 2][size / 2]
    }

    fun main() {
        val args = arrayOfNulls<String>(0)
        val n = if (args.size > 0) args[0]!!.toInt() else 100
        val left = calc(101)
        val right = -18.67
        if (Math.abs(left - right) > 0.1) {
            System.err.printf("%f != %f\n", left, right)
            System.exit(1)
        }
        val start_time = System.currentTimeMillis()
        val results = calc(n)
        time_diff = System.currentTimeMillis() - start_time
        println(results)
        System.out.printf("Time for the MatMul test: %f s\n", time_diff / 1e3)
    }

    fun results(): String {
        return String.format("Time for the MatMul test: %f s\n", time_diff / 1e3)
    }
}