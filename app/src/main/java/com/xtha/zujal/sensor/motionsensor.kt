package com.xtha.zujal.sensor

import android.app.Activity
import android.content.Context
import android.hardware.*
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class motionsensor : Activity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    private var sensoraccelormeter: Sensor? = null
    private var sensorgravity: Sensor? = null
    private var sensorgyroscope: Sensor? = null
    lateinit var textview1: TextView
    lateinit var textview2: TextView
    lateinit var textview3: TextView
    lateinit var textview4: TextView
    lateinit var textview5: TextView
    lateinit var textview6: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motionsensor)

        textview1 = findViewById(R.id.textview1)
        textview2 = findViewById(R.id.textview2)
        textview3 = findViewById(R.id.textview3)
        textview4 = findViewById(R.id.textview4)
        textview5 = findViewById(R.id.textview5)
        textview6 = findViewById(R.id.textview6)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensoraccelormeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if(sensoraccelormeter != null)
        {
            sensorManager.registerListener(this, sensoraccelormeter, SensorManager.SENSOR_DELAY_NORMAL)
        }
        else{
            textview1.setText("No accelerometer sensor")
        }

        sensorgravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

        sensorgyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        if(sensorgyroscope != null)
        {
            sensorManager.registerListener(this, sensorgyroscope, SensorManager.SENSOR_DELAY_NORMAL)
        }
        else{
            textview4.setText("No gyroscope sensor")
        }

        val sensoracceleration: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        val sensorrotaion: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        val sensorsignificantmotion: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION)
        val sensordetector: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        val sensorcounter: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        val sensorgyroscopeuncalibrated: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED)



        textview1 = findViewById(R.id.textview1)
        textview2 = findViewById(R.id.textview2)
        textview3 = findViewById(R.id.textview3)
        textview4 = findViewById(R.id.textview4)
        textview5 = findViewById(R.id.textview5)
        textview6 = findViewById(R.id.textview6)
    }

    override fun onSensorChanged(event: SensorEvent) {

        var mysensor : Sensor = event.sensor

        if(mysensor.type == Sensor.TYPE_ACCELEROMETER)
        {
            textview1.setText("x = "+event.values[0].toString())
            textview2.setText("y = "+event.values[1].toString())
            textview3.setText("x = "+event.values[2].toString())

            Log.d("TYPE_ACCELEROMETER", "x="+event.values[0]+ " y="+event.values[1]+" z="+event.values[2])
        }

        if(mysensor.type == Sensor.TYPE_GYROSCOPE)
        {
            textview4.setText("x = "+event.values[0].toString())
            textview5.setText("y = "+event.values[1].toString())
            textview6.setText("x = "+event.values[2].toString())

            Log.d("TYPE_GYROSCOPE", "x="+event.values[0]+ " y="+event.values[1]+" z="+event.values[2])
        }



    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
        println("Accuracy Changed");
        println(accuracy);
    }


    override fun onResume() {
        super.onResume()
        sensoraccelormeter?.also { accelometer ->
            sensorManager.registerListener(this, accelometer, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}
