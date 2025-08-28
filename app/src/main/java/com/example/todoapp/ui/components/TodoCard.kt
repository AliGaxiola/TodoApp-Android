package com.example.todoapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R

@Composable
fun TodoCard(
    title: String,
    description: String,
    isComplete: Boolean = false,
    onToggleComplete: () -> Unit,
    onClickDelete: () -> Unit,
    onClickEdit: () -> Unit // Nuevo parámetro para editar
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Row(
            Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = title,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.weight(0.2f))

            Row(
                modifier = Modifier.weight(0.8f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = "Edit Icon",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable { onClickEdit() },
                )
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = "Delete Icon",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable { onClickDelete() },
                )
                Icon(
                    painter = painterResource(
                        if (isComplete) R.drawable.ic_check_circle else R.drawable.ic_indeterminated
                    ),
                    contentDescription = "Complete Icon",
                    tint = if (isComplete) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable { onToggleComplete() },
                )
            }
        }
    }
}
