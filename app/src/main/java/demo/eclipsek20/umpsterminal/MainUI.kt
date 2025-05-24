package demo.eclipsek20.umpsterminal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainUI(modifier: Modifier = Modifier, getStatus: () -> String, getStatusTime: () -> Long) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            GreetingUI()
            StatusUI(getStatus = getStatus, getStatusTime = getStatusTime)
        }
    }
}

@Composable
fun GreetingUI(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ようこそ！",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun StatusUI(modifier: Modifier = Modifier, getStatus: () -> String, getStatusTime: () -> Long) {
    var status by remember { mutableStateOf(getStatus()) }
    var time_status_update by remember { mutableStateOf(getStatusTime()) }
    LaunchedEffect(Unit) {
        while (true) {
            status = getStatus()
            time_status_update = getStatusTime()
            var time_since_status_update = System.currentTimeMillis() / 1000 - time_status_update
            status = status + " (" + time_since_status_update + "s ago)"
            kotlinx.coroutines.delay(1000)
        }
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Last Status:",
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = status,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}



/* @SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UMPSTerminalDemoTheme {
        var tagStatus = mutableStateOf("No Tag Detected")
        MainUI(getStatus = { tagStatus.value })
    }
} */