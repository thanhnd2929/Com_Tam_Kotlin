package com.example.cum_tam_ph45160.Pages


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cum_tam_ph45160.TextField2
import com.example.cum_tam_ph45160.toColor

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UpdateInfoPage() {
    // Trạng thái lưu trữ giá trị nhập vào
    val phoneNumber = remember { mutableStateOf("") }
    val street = remember { mutableStateOf("") }
    val ward = remember { mutableStateOf("") }
    val houseNumber = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Ô nhập số điện thoại
        TextField2(
            title = "Số điện thoại",
            placeholder = "Nhập số điện thoại",
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it }
        )

        // Ô nhập đường
        TextField2(
            title = "Đường",
            placeholder = "Nhập đường",
            value = street.value,
            onValueChange = { street.value = it }
        )

        // Ô nhập phường
        TextField2(
            title = "Phường",
            placeholder = "Nhập phường",
            value = ward.value,
            onValueChange = { ward.value = it }
        )

        // Ô nhập nhà
        TextField2(
            title = "Số Nhà",
            placeholder = "Nhập số nhà",
            value = houseNumber.value,
            onValueChange = { houseNumber.value = it }
        )

        // Nút cập nhật thông tin
        Button(
            onClick = {

            },
            modifier = Modifier.padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = "#FE724C".toColor,
                contentColor = "#FFFFFF".toColor
            )
        ) {
            Text(text = "Cập nhật thông tin")
        }
    }
}
