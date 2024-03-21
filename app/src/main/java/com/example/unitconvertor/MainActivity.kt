package com.example.unitconvertor

import android.graphics.Paint.Style
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConvertor()
                }
            }
        }
    }
}


@Composable
fun UnitConvertor(){
    var inputVal by remember{ mutableStateOf("") }
    var outputVal by remember{ mutableStateOf("") }
    val dropVal1 = remember{ mutableStateOf("Meters") }
    val dropVal2 = remember{ mutableStateOf("Meters") }
    val button1 = remember { mutableStateOf(false) }
    val button2 = remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    fun unitConverter(){
        val inputVauleDouble = inputVal.toDoubleOrNull()?:0.0
        val result = (inputVauleDouble * conversionFactor.value * 100 / oConversionFactor.value).roundToInt()/100.0
        outputVal = result.toString()
    }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        //Here all elements will show below to the first one
        Text("Unit Convertor",
            fontSize = 30.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold
            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputVal, label = {Text("Enter the Number")}, onValueChange = {
            inputVal = it
            unitConverter()
            // Here goes what should happen, when data or value change
        })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Here all elements will show next to the first one

            Box{
                Button(onClick = {
                          button1.value = true
                }) {
                    Text("${dropVal1.value}")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "DropDown")
                }
                DropdownMenu(expanded = button1.value, onDismissRequest = { button1.value = false }) {
                    DropdownMenuItem(text = { Text("Centimeters")}, onClick = {
                        button1.value = false
                        dropVal1.value = "Centimeters"
                        conversionFactor.value = 0.01
                        unitConverter()
                    })
                    DropdownMenuItem(text = {Text("Meters")}, onClick = {
                        button1.value = false
                        dropVal1.value = "Meters"
                        conversionFactor.value = 1.00
                        unitConverter()
                    })
                    DropdownMenuItem(text = {Text("Feet") }, onClick = {
                        button1.value = false
                        dropVal1.value = "Feet"
                        conversionFactor.value = 0.3048
                        unitConverter()
                    })
                    DropdownMenuItem(text = {Text("Millimeters") }, onClick = {
                        button1.value = false
                        dropVal1.value = "Millimeters"
                        conversionFactor.value = 0.001
                        unitConverter()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = {
                    button2.value = true
                }) {
                    Text("${dropVal2.value}")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "DropDown")
                }
                DropdownMenu(expanded = button2.value, onDismissRequest = { button2.value = false }) {
                    DropdownMenuItem(text = { Text("Centimeters")}, onClick = {
                        button2.value = false
                        dropVal2.value = "Centimeters"
                        oConversionFactor.value = 0.01
                        unitConverter()

                    })
                    DropdownMenuItem(text = {Text("Meters")}, onClick = {
                        button2.value = false
                        dropVal2.value = "Meters"
                        oConversionFactor.value = 1.00
                        unitConverter()
                    })
                    DropdownMenuItem(text = {Text("Feet") }, onClick = {
                        button2.value = false
                        dropVal2.value = "Feet"
                        oConversionFactor.value = 0.3048
                        unitConverter()
                    })
                    DropdownMenuItem(text = {Text("Millimeters") }, onClick = {
                        button2.value = false
                        dropVal2.value = "Millimeters"
                        oConversionFactor.value = 0.001
                        unitConverter()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputVal ${dropVal2.value}")
    }
}

@Preview
@Composable
fun UnitConvertorPreview(){
    UnitConvertor()
}