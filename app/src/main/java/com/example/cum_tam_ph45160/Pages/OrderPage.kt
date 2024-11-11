package com.example.cum_tam_ph45160.Pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cum_tam_ph45160.Model.Order.Order
import com.example.cum_tam_ph45160.ViewModel.OrderViewModel
import com.example.cum_tam_ph45160.toColor
import java.text.SimpleDateFormat
import java.util.Locale

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrderPage(modifier: Modifier = Modifier) {

    val viewModelOrder: OrderViewModel = remember { OrderViewModel() }

    // Gọi API để lấy đơn hàng khi màn hình được hiển thị
    LaunchedEffect(Unit) {
        viewModelOrder.fetchOrder()
    }

    // Lấy danh sách đơn hàng từ ViewModel (ban đầu là `null`)
    val orders by viewModelOrder.orders.observeAsState(emptyList<Order>())

    // In ra log danh sách đơn hàng
    Log.d("oo", orders.toString())

    Column(
        modifier = modifier
            .fillMaxSize()
            .background("#F6F6F6".toColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Hiển thị danh sách đơn hàng bằng LazyColumn
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {

            orders?.let {
                items(it) { order ->
                    OrderCard(order = order)
                }
            }
        }
    }
}

@Composable
fun OrderCard(order: Order) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { showDialog = true },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors("#DDE0E3".toColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "ID: ${order.user}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Ngày thanh toán: ${formatDate(order.createdAt)}")
            Text(text = "Tổng tiền: \$${order.totalAmount}", fontSize = 14.sp, color = Color.Red)
        }
    }

    if (showDialog) {
        OrderDetailsDialog(order = order, onDismiss = { showDialog = false })
    }
}
@Composable
fun OrderDetailsDialog(order: Order, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Order Details") },
        text = {
            Column {
//                Text(text = "Order ID: ${order.id}")
                Text(text = "ID: ${order.user}")
                Text(text = "Tổng tiền: \$${order.totalAmount}")
                Text(text = "Địa chỉ: ${order.shippingAddress.address}")
                Text(text = "Ngày thanh toán: ${formatDate(order.createdAt)}")

                // Hiển thị sản phẩm trong đơn hàng
                order.items.forEach { item ->
                    Text(text = "Product: ${item.product.name}, Quantity: ${item.quantity}", fontSize = 14.sp)
                }
            }
        },
        confirmButton = {
            Text(
                text = "Close",
                modifier = Modifier.clickable { onDismiss() }
            )
        }
    )
}

// Hàm để định dạng ngày tháng
fun formatDate(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()) // Định dạng gốc
        val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()) // Định dạng mới
        val date = inputFormat.parse(dateString)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        dateString // Trả về nguyên văn nếu có lỗi
    }
}