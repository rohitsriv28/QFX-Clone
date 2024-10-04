package com.example.qfxclone.ui.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qfxclone.R
import com.example.qfxclone.ui.components.HeadingBox
import com.example.qfxclone.ui.components.HeroSection
import com.example.qfxclone.ui.components.MovieWood
import com.example.qfxclone.ui.components.MyDefaultValues

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun ComingSoon() {
    Column(Modifier.verticalScroll(ScrollState(1))) {
        TopAppBar(
            title = {},
            navigationIcon = {
                Row(Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp)) {
                    IconButton(onClick = {

                    }) {
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
        HeroSection()
        HeadingBox(title = "Coming Soon", movies = "(5 Movies)")
        Column() {
            MovieWood(title = "Bollywood", movies = "(2)")
            MovieWood(title = "Hollywood", movies = "(2)")
            MovieWood(title = "Kollywood", movies = "(2)")
        }
    }
}