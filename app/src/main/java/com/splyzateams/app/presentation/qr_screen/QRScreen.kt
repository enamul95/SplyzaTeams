package com.splyzateams.app.presentation.qr_screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.splyzateams.app.common.Constrants
import com.splyzateams.app.presentation.Screen
import com.splyzateams.app.presentation.inivite_members.InviteTeamViewModel
import com.splyzateams.app.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QRScreen(
    navController: NavController,
) {

    val context = LocalContext.current
    val url = navController.previousBackStackEntry?.savedStateHandle?.get<String>(
        Constrants.QR_URL
    )

    Scaffold(

        topBar = {
            TopAppBar(
                elevation = 4.dp,
                title = {
                    Text(
                        Constrants.QR_TITLE,
                        color = Color.White
                    )

                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.TeamsScreen.route)
                        },
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = Constrants.BACK,
                            tint = Color.White
                        )
                    }

                },
                backgroundColor = Color.Blue

            )
        },

        content = {
            if (url != null) {
                QrCode(url)
            }
        },
        backgroundColor = Color(0xFFEDEAE0)

    )


}

@Composable
fun QrCode(url:String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        AndroidView(modifier = Modifier.fillMaxSize(),
            factory = { context ->
                val rootView = LayoutInflater.from(context).inflate(R.layout.my_qr_code, null, false)
                val ivQr= rootView.findViewById<ImageView>(R.id.ivQr)

                try {
                    val writer = QRCodeWriter()
                    val bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, 512, 512)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                    for (x in 0 until width) {
                        for (y in 0 until height) {
                            bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
                        }
                    }
                    ivQr.setImageBitmap(bitmap)
                }catch (e:Exception){
                    e.printStackTrace()
                }

                rootView
            })

    }
}
