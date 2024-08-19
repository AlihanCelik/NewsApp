package com.example.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NewButton(
    text:String,
    onClick:()->Unit
){
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(
        contentColor = Color.White,
        containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(size = 6.dp )
        ) {
            Text(text = text,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold))

    }

}
@Composable
fun NewTextButton(
    text: String,
    onClick: () -> Unit
){
    TextButton(onClick = onClick) {
        Text(text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Color.White)
        
    }
}