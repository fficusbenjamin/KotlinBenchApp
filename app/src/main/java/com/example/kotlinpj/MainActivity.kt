package com.example.kotlinpj

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Switch
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinpj.Benchmarks.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    val camera_time = System.currentTimeMillis()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val info = findViewById<TextView>(R.id.showInfo)
        info.text = DeviceInfo.fullDeviceName
    }

    @Throws(IOException::class)
    fun cameraTest(view: View?) {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5)
        startActivityForResult(intent, 1)
    }

    @Throws(Exception::class)
    fun selTests(view: View?) {
        val base64Sw = findViewById<Switch>(R.id.base64Switch)
        val brainfSw = findViewById<Switch>(R.id.brainSwitch)
        val matSw = findViewById<Switch>(R.id.matmulSwitch)
        val faSwitch = findViewById<Switch>(R.id.fastaSwitch)
        val fannSwitch = findViewById<Switch>(R.id.fannSwitch)
        val nbodySwitch = findViewById<Switch>(R.id.nbodySwitch)
        val info = findViewById<TextView>(R.id.showInfo)
        if (base64Sw.isChecked) {
            println("BASE64")
            val base64 = Base64Test
            //Base64Test.main()
            base64.main()
            info.text = Base64Test.results()
        }
        if (brainfSw.isChecked) {
            println("BRAINTEST")
            BrainTest.main()
            info.text = BrainTest.results()
        }
        if (matSw.isChecked) {
            println("MATMUL")
            MatMulTest.main()
            info.text = MatMulTest.results()
        }
        if (faSwitch.isChecked) {
            println("FASTA")
            val fastaTest = FastaTest()
            fastaTest.runBenchmark()
            info.text = fastaTest.results()
        }
        if (fannSwitch.isChecked) {
            println("FANNKUCK")
            val fannkuchTest = FannkuchTest()
            fannkuchTest.runBenchmark()
            info.text = FannkuchTest.results()
        }
        if (nbodySwitch.isChecked) {
            println("NBODY")
            val nbody = NBodyTest()
            nbody.runBenchmark()
            info.text = NBodyTest.results()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1) {
            val builder = AlertDialog.Builder(this)
            val videoView = VideoView(this)
            videoView.setVideoURI(data!!.data)
            videoView.start()
            builder.setView(videoView).show()
            val info = findViewById<TextView>(R.id.showInfo)
            time_diff = System.currentTimeMillis() - camera_time
            info.text = String.format("Time for the Camera test: %f s\n", time_diff / 1e3)
        }
    }

    companion object {
        var time_diff: Long = 0
    }
}