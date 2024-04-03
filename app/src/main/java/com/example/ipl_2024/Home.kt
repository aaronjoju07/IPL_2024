package com.example.ipl_2024

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ipl_2024.ui.theme.IPL_2024Theme

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPL_2024Theme {
                val players = listOf(
                    Player("Ruturaj Gaikwad", "Batter", R.drawable.ruturaj_gaikwad),
                    Player("MS Dhoni", "Wicketkeeper Batter", R.drawable.ms_dhoni),
                    Player("Ajinkya Rahane", "Batter", R.drawable.ajinkya_rahane),
                    Player("Shaik Rasheed", "Batter", R.drawable.shaik_rasheed),
                    Player("Avanish Rao Aravelly", "Batter", R.drawable.avanish_rao_aravelly)
                )

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    Greeting("Players",players)

                }
            }
        }
    }
}

data class Player(val name: String, val position: String, val imageId: Int)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, players: List<Player>, modifier: Modifier = Modifier) {
    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            TopAppBar(title = { Text(text = "$name", fontSize = 28.sp, fontWeight = FontWeight.Bold,)}, modifier = Modifier.clip(RoundedCornerShape(15.dp)))
            ButIntent()
            LazyColumn {
                items(players) { player ->
                    Box(modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .fillMaxWidth()
                        .background(Color(0xFF343434))
                        .pointerInput(true) {
                            detectTapGestures(onLongPress = { showMenu = true })
                        }
                    ){
                        Row (modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically){
                            Image(painter = painterResource(id = player.imageId), contentDescription =null, modifier = Modifier.size(150.dp) )
                            Column {
                                Text(
                                    text = player.name,
                                    fontSize = 25.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = player.position,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu=false }) {
                        DropdownMenuItem(text = { Text(text = "View Details")}, onClick = {
                            Toast.makeText(context, "Name: ${player.name}", Toast.LENGTH_SHORT).show()
                            showMenu = false
                        })
                        DropdownMenuItem(text = { Text(text = "View Position")}, onClick = {
                            Toast.makeText(context, "Position: ${player.position}", Toast.LENGTH_SHORT).show()
                            showMenu = false
                        })
                    }

                }

            }
            ButIntent()
        }
    }
}
@Composable
fun ButIntent() {
    var context = LocalContext.current
    var intent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iplt20.com/"))
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .clip(CircleShape)
        .padding(horizontal = 20.dp, vertical = 5.dp)
        .clickable { context.startActivity(intent) }
        .background(Color(0xFF999999))){

        Text(text = "Watch Live", color = Color.Black, modifier = Modifier.padding(16.dp))
    }
}
