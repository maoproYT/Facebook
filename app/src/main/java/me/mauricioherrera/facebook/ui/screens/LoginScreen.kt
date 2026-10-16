package me.mauricioherrera.facebook.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val FacebookBlue = Color(0xFF1877F2)
private val FacebookGreen = Color(0xFF42B72A)
private val FacebookBorderGray = Color(0xFFCCD0D5)
private val FacebookTextGray = Color(0xFF606770)

@Composable
fun FacebookLoginScreen(
    onClickRegister: () -> Unit,
    onClickSignIn: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "facebook",
            color = FacebookBlue,
            fontSize = 52.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-1).sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        FacebookTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Correo electrónico o número de teléfono",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(12.dp))

        FacebookTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Contraseña",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            isPassword = true,
            passwordVisible = passwordVisible,
            onTogglePasswordVisibility = {
                passwordVisible = !passwordVisible
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onClickSignIn,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = FacebookBlue
            ),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                text = "Iniciar sesión",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = {
            // Aquí puedes implementar la recuperación de contraseña.
        }) {
            Text(
                text = "¿Olvidaste tu contraseña?",
                color = FacebookTextGray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = FacebookBorderGray
            )

            Text(
                text = "o",
                modifier = Modifier.padding(horizontal = 12.dp),
                color = FacebookTextGray,
                fontSize = 14.sp
            )

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = FacebookBorderGray
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onClickRegister,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = FacebookGreen
            ),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                text = "Crear cuenta nueva",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Meta © 2024",
            modifier = Modifier.padding(bottom = 24.dp),
            color = FacebookTextGray,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FacebookTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePasswordVisibility: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(placeholder, color = FacebookTextGray, fontSize = 16.sp) },
        singleLine = true,
        shape = RoundedCornerShape(6.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        visualTransformation = if (isPassword && !passwordVisible)
            PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = onTogglePasswordVisibility) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.VisibilityOff
                        else Icons.Filled.Visibility,
                        contentDescription = if (passwordVisible) "Ocultar contraseña"
                        else "Mostrar contraseña",
                        tint = FacebookTextGray
                    )
                }
            }
        } else null,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = FacebookBlue,
            unfocusedBorderColor = FacebookBorderGray
        )
    )
}