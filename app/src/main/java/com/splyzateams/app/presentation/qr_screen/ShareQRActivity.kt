package com.splyzateams.app.presentation.qr_screen
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.splyzateams.app.R
import com.splyzateams.app.presentation.Screen


class ShareQRActivity : AppCompatActivity() {


    private lateinit var btnBack:Button
    private lateinit var ivQr:ImageView
    private var qrImage: Bitmap? = null

    var url = "https://www.espncricinfo.com";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_qractivity)

        btnBack = findViewById(R.id.btnBack)
        ivQr = findViewById(R.id.ivQr)

        btnBack.setOnClickListener {
            var navController = NavController(this@ShareQRActivity)
            navController.navigate(Screen.TeamsScreen.route)
        }

       // generateQR()

    }

    fun generateQR(){

        try {
            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            ivQr.setImageBitmap(bitmap)
        }catch (e:Exception){
            e.printStackTrace()
        }


    }
}