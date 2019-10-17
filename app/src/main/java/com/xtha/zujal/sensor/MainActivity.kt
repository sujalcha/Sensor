package com.xtha.zujal.sensor

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.nio.file.Files.size



class MainActivity : AppCompatActivity() {

    private lateinit var sensorManager: SensorManager
    private var mSensor: Sensor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        println(deviceSensors)

        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
           println("Success! There's a magnetometer.")
        } else {
            println("Failure! No magnetometer.")
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null) {
            val gravSensors = sensorManager.getSensorList(Sensor.TYPE_GRAVITY)
            for (i in gravSensors.indices) {
                if (gravSensors[i].vendor.contains("Google LLC") && gravSensors[i].version == 3) {
                    mSensor = gravSensors[i]
                    println("// Use the version 3 gravity sensor.")
                }
            }
        }
        if (mSensor == null) {
            // Use the accelerometer.
            if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
                mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                println("  Use the accelerometer.")
            } else {
                println(" Sorry, there are no accelerometers on your device. You can't play this game.")
                null
            }
        }

        val intent = Intent(this, environmentsensor::class.java)
        startActivity(intent)

    }
}
