
package me.mauricioherrera.facebook
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

private val FacebookBlue = Color(0xFF1877F2)
private val FacebookBorderGray = Color(0xFFCCD0D5)
private val FacebookTextGray = Color(0xFF606770)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacebookRegisterScreen(navController: NavController) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailOrPhone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Mujer") }
    var passwordVisible by remember { mutableStateOf(false) }

    val genderOptions = listOf(
        "Mujer",
        "Hombre",
        "Personalizado"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Ir atrás"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Crea una cuenta",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1C1E21)
            )

            Text(
                text = "Es rápido y fácil.",
                fontSize = 14.sp,
                color = FacebookTextGray,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Nombre y apellido
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    shape = RoundedCornerShape(6.dp)
                )

                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Apellido") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    shape = RoundedCornerShape(6.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Correo electrónico o teléfono
            OutlinedTextField(
                value = emailOrPhone,
                onValueChange = { emailOrPhone = it },
                label = {
                    Text("Número de celular o correo electrónico")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(6.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña nueva") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (passwordVisible) {
                    androidx.compose.ui.text.input.VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (passwordVisible) {
                                Icons.Default.VisibilityOff
                            } else {
                                Icons.Default.Visibility
                            },
                            contentDescription = if (passwordVisible) {
                                "Ocultar contraseña"
                            } else {
                                "Mostrar contraseña"
                            },
                            tint = FacebookTextGray
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(6.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Fecha de nacimiento
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Fecha de nacimiento",
                    fontSize = 12.sp,
                    color = FacebookTextGray,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                OutlinedTextField(
                    value = birthDate,
                    onValueChange = { birthDate = it },
                    placeholder = { Text("DD/MM/AAAA") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    shape = RoundedCornerShape(6.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Selección de género
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Género",
                    fontSize = 12.sp,
                    color = FacebookTextGray,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    genderOptions.forEach { option ->

                        Row(
                            modifier = Modifier
                                .selectable(
                                    selected = selectedGender == option,
                                    onClick = {
                                        selectedGender = option
                                    },
                                    role = Role.RadioButton
                                )
                                .padding(end = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedGender == option,
                                onClick = {
                                    selectedGender = option
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = FacebookBlue
                                )
                            )

                            Text(
                                text = option,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de registro
            Button(
                onClick = {
                    // Aquí implementarás el registro.
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FacebookBlue
                ),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = "Registrarte",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Volver al inicio de sesión
            TextButton(
                onClick = {
                    navController.navigate("login") {
                        popUpTo("login") {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            ) {
                Text(
                    text = "¿Ya tienes una cuenta?",
                    color = FacebookBlue
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Meta © 2024",
                modifier = Modifier.padding(bottom = 24.dp),
                color = FacebookTextGray,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
