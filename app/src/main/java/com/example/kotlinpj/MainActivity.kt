package com.example.kotlinpj

import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinpj.Benchmarks.*



class MainActivity : AppCompatActivity() {
    //val camera_time = System.currentTimeMillis()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val info = findViewById<TextView>(R.id.showInfo)
        info.text = DeviceInfo.fullDeviceName
    }


    fun cameraTest(view: View?) {
        /*val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5)
        startActivityForResult(intent, 1)*/
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
            val start_time = System.currentTimeMillis()
            for(i in 1..100) {
                MatMulTest.main()
                println(MatMulTest.results())
            }
            val time_diff = System.currentTimeMillis() - start_time;
            val result = String.format("Time for the MatMul test: %f s\n", time_diff / 1e3)
            info.text = result
        }
        if (faSwitch.isChecked) {
            println("FASTA")
            val fastaTest = FastaTest()
            val start_time = System.currentTimeMillis()
            for(i in 1..100) {
                fastaTest.runBenchmark()
                println(fastaTest.results())
            }
            val time_diff = System.currentTimeMillis() - start_time;
            val result = String.format("Time for the Fasta test: %f s\n", time_diff / 1e3)
            info.text = result
        }
        if (fannSwitch.isChecked) {
            println("FANNKUCK")
            val fannkuchTest = FannkuchTest()
            val start_time = System.currentTimeMillis()
            for (i in 1..100) {
                fannkuchTest.runBenchmark()
                println(FannkuchTest.results())
            }
            val time_diff = System.currentTimeMillis() - start_time;
            val result = String.format("Time for the Fannkuch test: %f s\n", time_diff / 1e3)
            info.text = result
        }
        if (nbodySwitch.isChecked) {
            println("NBODY")
            val nbody = NBodyTest()
            val start_time = System.currentTimeMillis()
            for(i in 1..100) {
                nbody.runBenchmark()
                println(NBodyTest.results())
            }
            val time_diff = System.currentTimeMillis() - start_time;
            val result = String.format("Time for the NBody test: %f s\n", time_diff / 1e3)
            info.text = result
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
    }*/

    /*companion object {
        var time_diff: Long = 0
    }*/
}