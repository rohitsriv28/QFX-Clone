package com.example.qfxclone.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qfxclone.R
import com.example.qfxclone.ui.components.ExpandableDateCard
import com.example.qfxclone.ui.components.MovieSelectionCard
import com.example.qfxclone.ui.components.MyDefaultValues

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showSystemUi = true)
@Composable
fun BookNow() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    Row(Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp)) {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(36.dp)
                                    .clickable { },
                                tint = Color.White
                            )
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.nfxlogo),
                            contentDescription = "",
                            tint = Color(245, 234, 29, 255),
                            modifier = Modifier
                                .weight(0.1f)
                                .size(50.dp)
                        )
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable { },
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable { },
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MyDefaultValues.defaultBgColor)
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(50.dp),
                containerColor = MyDefaultValues.defaultBgColor
            ) {

            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(0.dp, 16.dp, 0.dp, 12.dp)
        ) {
            Box(
                Modifier
                    .background(MyDefaultValues.defaultBgColor)
                    .fillMaxWidth()
                    .height(60.dp),
            ) {
                Column {
                    Text(
                        text = "Now Showing",
                        modifier = Modifier.fillMaxWidth(),
                        color = MyDefaultValues.defaultButtonColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Book Your Movie",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(245, 234, 29, 255),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(MyDefaultValues.defaultBookingSpace)
            MovieSelectionCard()
            Spacer(MyDefaultValues.defaultBookingSpace)
            ExpandableDateCard()
        }
    }
}