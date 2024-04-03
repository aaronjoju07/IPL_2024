package com.example.ipl_2024

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ipl_2024.ui.theme.IPL_2024Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPL_2024Theme {
                // A surface container using the 'background' color from the theme
                var context = LocalContext.current
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF332941)
                ) {
                    EventRegistration(context)
                }
            }
        }
    }
}

@Composable
fun EventRegistration(context: Context) {
    var currentStep by remember { mutableStateOf(0) }

    val steps = listOf(
        "Name",
        "PhoneNumber",
        "FavoriteTeam",
        "FavoritePlayer",
        "Country"
    )

    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var favoriteTeam by remember { mutableStateOf("") }
    var favoritePlayer by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Your existing UI code here
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color(0xFF3B3486))
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "IPL-24",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = Color(0xFFEEEEEE)
                )
                when (currentStep) {
                    0 -> NameStep(name = name, onNameChange = { name = it })
                    1 -> SessionPreferencesStep(
                        sessionPreferences = phoneNumber,
                        onSessionPreferencesChange = { phoneNumber = it },
                        name = name
                    )
                    2 -> SessionPreferencesStep(
                        sessionPreferences = favoriteTeam,
                        onSessionPreferencesChange = { favoriteTeam = it },
                        name = name
                    )
                    3 -> SessionPreferencesStep(
                        sessionPreferences = favoritePlayer,
                        onSessionPreferencesChange = { favoritePlayer = it },
                        name = name
                    )
                    4 -> SessionPreferencesStep(
                        sessionPreferences = country,
                        onSessionPreferencesChange = { country = it },
                        name = name
                    )
                }

                // Next button for all steps except the last one
                if (currentStep < steps.size - 1) {
                    Button(
                        onClick = {
                            currentStep++
                        },
                        colors = ButtonDefaults.buttonColors(Color(0xFF222831)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(5.dp)
                    ) {
                        Text(text = "Next: ${steps[currentStep + 1]}")
                    }
                } else {
                    // Last step - Submit Button
                    Button(
                        onClick = {
                            val intent = Intent(context, Home::class.java)
                            context.startActivity(intent)
                        }, colors = ButtonDefaults.buttonColors(Color(0xFF222831)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(5.dp)
                    ) {
                        Text(text = "Submit")
                    }
                }
                // Back button for all steps except the first one
                if (currentStep > 0) {
                    Button(
                        onClick = {
                            currentStep--
                        },colors = ButtonDefaults.buttonColors(Color(0xFF222831)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(5.dp)
                    ) {
                        Text(text = "Back")
                    }
                }
            }
        }
    }
}


@Composable
fun SessionPreferencesStep(
    sessionPreferences: String,
    onSessionPreferencesChange: (String) -> Unit,
    name: String
) {
    TextField(
        value = sessionPreferences,
        onValueChange = { onSessionPreferencesChange(it) },
        label = { Text(text = "") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
    Text(
        text = "Name: $name",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 16.dp),color = Color.White
    )
}

@Composable
fun DietaryRestrictionsStep(
    dietaryRestrictions: String,
    onDietaryRestrictionsChange: (String) -> Unit,
    name: String,
    sessionPreferences: String
) {
    TextField(
        value = dietaryRestrictions,
        onValueChange = { onDietaryRestrictionsChange(it) },
        label = { Text(text = "Favorite Team") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
    Text(
        text = "Name: $name\nSession Preferences: $sessionPreferences",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 16.dp),color = Color.White
    )
}

@Composable
fun NetworkingInterestsStep(
    networkingInterests: String,
    onNetworkingInterestsChange: (String) -> Unit,
    name: String,
    sessionPreferences: String,
    dietaryRestrictions: String
) {
    TextField(
        value = networkingInterests,
        onValueChange = { onNetworkingInterestsChange(it) },
        label = { Text(text = "Favorite Player") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
    Text(
        text = "Name: $name\nSession Preferences: $sessionPreferences\nDietary Restrictions: $dietaryRestrictions",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 16.dp),
        color = Color.White
    )
}

@Composable
fun NameStep(name: String, onNameChange: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = { onNameChange(it) },
        label = { Text(text = "Name") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Composable
fun SessionPreferencesStep(sessionPreferences: String, onSessionPreferencesChange: (String) -> Unit) {
    TextField(
        value = sessionPreferences,
        onValueChange = { onSessionPreferencesChange(it) },
        label = { Text(text = "") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Composable
fun DietaryRestrictionsStep(dietaryRestrictions: String, onDietaryRestrictionsChange: (String) -> Unit) {
    TextField(
        value = dietaryRestrictions,
        onValueChange = { onDietaryRestrictionsChange(it) },
        label = { Text(text = "") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Composable
fun NetworkingInterestsStep(networkingInterests: String, onNetworkingInterestsChange: (String) -> Unit) {
    TextField(
        value = networkingInterests,
        onValueChange = { onNetworkingInterestsChange(it) },
        label = { Text(text = "Networking Interests") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}