package com.xtha.zujal.sensor

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class environmentsensor : Activity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensorpressure: Sensor? = null
    private var sensorambitemtparature: Sensor? = null
    private var sensorlight: Sensor? = null
    private var sensorhumidity: Sensor? = null
    lateinit var pressuretext: TextView
    lateinit var lighttext: TextView
    lateinit var temparaturetext: TextView
    lateinit var humiditytext: TextView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_environmentsensor)

        pressuretext = findViewById(R.id.idpressure)
        lighttext = findViewById(R.id.idlight)
        temparaturetext = findViewById(R.id.idtemparature)
        humiditytext = findViewById(R.id.idhumidity)

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensorpressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        if(sensorpressure != null)
        {
            sensorManager.registerListener(this, sensorpressure, SensorManager.SENSOR_DELAY_NORMAL)
        }
        else{
            pressuretext.setText("No pressuretext sensor")
        }

        sensorambitemtparature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        if(sensorambitemtparature != null)
        {
            sensorManager.registerListener(this, sensorambitemtparature, SensorManager.SENSOR_DELAY_NORMAL)
        }
        else{
            temparaturetext.setText("No temparaturetext sensor")
        }

        sensorlight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        if(sensorlight != null)
        {
            sensorManager.registerListener(this, sensorlight, SensorManager.SENSOR_DELAY_NORMAL)
        }
        else{
            lighttext.setText("No lighttext sensor")
        }
        sensorhumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        if(sensorhumidity != null)
        {
            sensorManager.registerListener(this, sensorhumidity, SensorManager.SENSOR_DELAY_NORMAL)
        }
        else{
            humiditytext.setText("No humiditytext sensor")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        var mysensor : Sensor = event.sensor

        if(mysensor.type == Sensor.TYPE_PRESSURE)
        {
            pressuretext.setText("pressuretext = "+event.values[0].toString())
            Log.d("pressuretext", "pressuretext="+event.values[0])
        }
        if(mysensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE)
        {
            temparaturetext.setText("temparaturetext = "+event.values[0].toString())
            Log.d("temparaturetext", "temparaturetext="+event.values[0])
        }
        if(mysensor.type == Sensor.TYPE_LIGHT)
        {
            lighttext.setText("lighttext = "+event.values[0].toString())
            Log.d("lighttext", "lighttext="+event.values[0])
        }
        if(mysensor.type == Sensor.TYPE_RELATIVE_HUMIDITY)
        {
            humiditytext.setText("humiditytext = "+event.values[0].toString())
            Log.d("humiditytext", "humiditytext="+event.values[0])
        }
    }

    override fun onResume() {
        // Register a listener for the sensor.
        super.onResume()
        sensorManager.registerListener(this, sensorpressure, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, sensorlight, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}