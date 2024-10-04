package com.example.qfxclone.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qfxclone.R
import com.example.qfxclone.ui.components.MyDefaultValues

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun UserProfile() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Profile", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go Back"/*, tint = Color.White*/
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box(
                Modifier
                    .background(Color(228, 141, 141, 255))
                    .fillMaxWidth()
                    .height(290.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        Modifier
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(50)
                            )
                            .size(150.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "User's Image",
                            modifier = Modifier.clip(shape = RoundedCornerShape(50))
                        )
                    }
                    Text(text = "User Name")
                    Text(text = "Email")
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(ScrollState(1)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "User Info", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                Column(
                    Modifier
                        .padding(14.dp)
                        .fillMaxWidth()
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Column {
                            Text(text = "Name", fontWeight = FontWeight.SemiBold)
                            Text(text = "Bruce Wayne")
                        }
                    }
                    Spacer(MyDefaultValues.defaultSpace)
                    Row {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Column {
                            Text(text = "Phone", fontWeight = FontWeight.SemiBold)
                            Text(text = "+977............")
                        }
                    }
                    Spacer(MyDefaultValues.defaultSpace)
                    Row {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Column {
                            Text(text = "Email", fontWeight = FontWeight.SemiBold)
                            Text(text = "batman@wayne.com")
                        }
                    }
                    Spacer(MyDefaultValues.defaultSpace)
                    Row {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Column {
                            Text(text = "Address", fontWeight = FontWeight.SemiBold)
                            Text(text = "Gotham City")
                        }
                    }
                    Spacer(MyDefaultValues.defaultSpace)
                    Row {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Column {
                            Text(text = "Date of Birth", fontWeight = FontWeight.SemiBold)
                            Text(text = "Feb 19")
                        }
                    }
                }
            }
        }

    }
}