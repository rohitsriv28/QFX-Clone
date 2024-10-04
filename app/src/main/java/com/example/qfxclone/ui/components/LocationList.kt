
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun MyScreen() {
    var isExpanded by remember { mutableStateOf(false) }
    val places = listOf(
        "Select",
        "Birgunj",
        "Kathmandu",
        "Biratnagar",
        "Butwal",
        "Nepalgunj",
        "Damauli",
        "Itahari"
    )
    var selectedPlaceText by remember { mutableStateOf(places[0]) }

    IconButton(onClick = { isExpanded = !isExpanded }) {
        Icon(Icons.Default.LocationOn, contentDescription = "Expand", tint = Color.White)
        if (isExpanded) {
            LocationList(places, selectedPlaceText) { place ->
                selectedPlaceText = place
                isExpanded = false
            }
        }
    }
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun MyScreen() {
    var isExpanded by remember { mutableStateOf(false) }
    val places = listOf(
        "Select",
        "Birgunj",
        "Kathmandu",
        "Biratnagar",
        "Butwal",
        "Nepalgunj",
        "Damauli",
        "Itahari"
    )
    var selectedPlaceText by remember { mutableStateOf(places[0]) }

    if (isExpanded) {
        LocationList(places, selectedPlaceText) { place ->
            selectedPlaceText = place
            isExpanded = false
        }
    } else {
        OutlinedTextField(
            modifier = Modifier
                .clickable { isExpanded = true },
            value = selectedPlaceText,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { Icon(Icons.Default.LocationOn, contentDescription = "Expand", tint = Color.Black) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            shape = ShapeDefaults.Medium,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.None
            )
        )
    }
}*/


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationList(
    places: List<String>,
    selectedPlaceText: String,
    onPlaceSelected: (String) -> Unit
) {
    ExposedDropdownMenuBox(
        expanded = true,
        onExpandedChange = { },
        modifier = Modifier.width(170.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.menuAnchor(),
            value = selectedPlaceText,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = true) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            shape = ShapeDefaults.Medium,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.None
            )
        )
        ExposedDropdownMenu(
            modifier = Modifier
                .background(Color.LightGray)
                .width(160.dp)
                .height(450.dp),
            expanded = true,
            onDismissRequest = {  },
        ) {
            places.forEach { place ->
                DropdownMenuItem(
                    text = {
                        Text(place, color = Color.Black)
                    },
                    onClick = {
                        onPlaceSelected(place)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
