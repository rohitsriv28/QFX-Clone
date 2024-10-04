package com.example.qfxclone.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter


//@Preview(showSystemUi = true)
@Composable
fun MovieSelectionCard() {
    var expanded by remember {
        mutableStateOf(false)
    }
    val options =
        listOf(
            "Teri Baaton Me Aisa Uljha Jiya", "Fighter", "DeadPool & Wolverine", "Madame Web"
        )
    var selectedOption by remember {
        mutableStateOf("")
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(
            containerColor = MyDefaultValues.defaultButtonColor
        )
    ) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedOption.ifEmpty { options[0] }, fontSize = 16.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

            }
            if (expanded) {
                LazyColumn {
                    items(options) { option ->
                        Text(
                            text = option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedOption = option
                                    expanded = false
                                }
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpandableDateCard() {
    val today = LocalDate.now()
    val dates = listOf(today, today.plusDays(1), today.plusDays(2))
    var expanded by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(today) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(MyDefaultValues.defaultButtonColor)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = selectedDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")),
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            if (expanded) {
                LazyColumn {
                    items(dates) { date ->
                        DateItem(date = date) {
                            selectedDate = it
                            expanded = false
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateItem(date: LocalDate, onItemClick: (LocalDate) -> Unit) {
    val dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
    Text(
        text = dateFormatter.format(date),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onItemClick(date) }
    )
}


@Preview(showSystemUi = true)
@Composable
fun SeatSelection() {
    val selectedSeats = remember { mutableStateListOf<Int>() }

    LazyVerticalGrid(columns = GridCells.Fixed(10)) {
        items((1..100).toList()) { seatNumber ->
            val isSelected = selectedSeats.contains(seatNumber)
            SeatItems(
                seatNumber.toString(),
                if (isSelected) MyDefaultValues.defaultButtonColor else Color.Transparent
            ) {
                if (isSelected) {
                    selectedSeats.remove(seatNumber)
                } else {
                    selectedSeats.add(seatNumber)
                }
            }
        }

        item(
            span = {
                GridItemSpan(10)
            }
        ) {
            Text(
                text = "Selected Seats: ${selectedSeats.joinToString(", ")}",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SeatItems(seatNumber: String, selectedSeatColor: Color, onSeatSelected: () -> Unit = {}) {
    Box(
        Modifier
            .size(45.dp)
            .padding(4.dp)
            .border(BorderStroke(2.dp, Color.Black), ShapeDefaults.Small)
            .clickable { onSeatSelected() }
            .background(selectedSeatColor, ShapeDefaults.Small),
        contentAlignment = Alignment.Center
    ) {
        Text(text = seatNumber)
    }
}
