package com.example.myapplication

import android.content.ContentValues.TAG
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() , SensorEventListener  {
         var sensor : Sensor?=null
         var sensorManager : SensorManager?= null
        lateinit var compassImage:ImageView
        lateinit var rotationnTV : TextView


    var currentDegree = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        compassImage = findViewById(R.id.imageView)
        rotationnTV=findViewById(R.id.textView3)


        }

     override fun onSensorChanged(event: SensorEvent?) {
        var degree = Math.round(event!!.values[0])
         rotationnTV.text=degree.toString() + "°"
         var rotationAnimaion = RotateAnimation(currentDegree,(-degree).toFloat()
             ,Animation.RELATIVE_TO_SELF, 0.5f,
             Animation.RELATIVE_TO_SELF, 0.5f)
         rotationAnimaion.duration=210
         rotationAnimaion.fillAfter=true

         compassImage.startAnimation(rotationAnimaion)
         currentDegree=-degree.toFloat()
         rotationnTV.text=degree.toString()
     }

     override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
         Log.d(TAG, "Accuracy changed: $accuracy")
     }

    override fun onResume() {
        super.onResume( )
        sensorManager?.registerListener(this , sensor , SensorManager.SENSOR_DELAY_GAME
        )
    }
       override fun onPause() {

       super.onPause()
       sensorManager?.unregisterListener(this)

    }
 }

