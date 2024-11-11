package com.example.cum_tam_ph45160.Pages

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cum_tam_ph45160.LoginScreen
import com.example.cum_tam_ph45160.Retrofit.RetrofitClient
import com.example.cum_tam_ph45160.toColor

@Composable
fun SupportPage(navController: NavController ,modifier: Modifier = Modifier) {

    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) } // Trạng thái cho dialog

    Column (
        modifier = modifier.fillMaxSize()
            .background("#F6F6F6".toColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        // Card for Logout
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    showDialog = true
                }
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White // Màu chữ
            )
        ) {
            Text(
                text = "Logout",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }

        // Card for Manage
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    navController.navigate("manage") // Điều hướng tới trang quản lý
                }
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF03A9F4),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Manage",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    Text(text = "Đăng Xuẩt")
                },
                text = {
                    Text("Bạn có muốn đăng xuất không ?")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            logoutUser(context)
                            showDialog = false
                        }
                    ) {
                        Text("Logout")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }

            )
        }

    }

}

fun logoutUser(context: Context) {
    // Lấy SharedPreferences
    val sharedPref = context.getSharedPreferences("MY_APP_PREFS", MODE_PRIVATE)
    val editor = sharedPref.edit()

    // Xóa token khỏi SharedPreferences
    editor.remove("auth_token")
    RetrofitClient.token = null
    editor.apply()

    // Chuyển hướng về LoginScreen
    val intent = Intent(context, LoginScreen::class.java)
    intent.flags =
        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Xóa stack activity trước đó
    context.startActivity(intent)

    // Kết thúc Activity hiện tại (nếu có)
    if (context is Activity) {
        context.finish()
    }
}
