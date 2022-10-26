package com.test.wsafocus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.test.wsafocus.ui.theme.CustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Row(
                        modifier = Modifier.padding(start = 16.dp),
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "ScrollView:",
                                color = MaterialTheme.colors.primary,
                                textAlign = TextAlign.Left,
                                maxLines = 1,
                            )
                            Text(
                                text = "Any scrollable view on the screen. This could be a sibling view as shown is this example, or even the parent scrollable view which the target TextField is embedded inside.",
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.Left,
                            )
                            ScrollableView()
                        }
                        ViewWithInput(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun ScrollableView() {
    return LazyColumn {
        items(count = 100) { index ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Item: $index",
                    textAlign = TextAlign.Left,
                )
            }
        }
    }
}

@Composable
fun ViewWithInput(modifier: Modifier) {
    var textEntered by remember { mutableStateOf("") }
    var displayedText by remember { mutableStateOf("") }
    Column(modifier = modifier) {
        Text(
            text = "TextField:",
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Left,
            maxLines = 1,
        )
        Row {
            TextField(
                modifier = Modifier.wrapContentSize(),
                value = textEntered,
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        displayedText = textEntered
                    },
                ),
                onValueChange = { textEntered = it },
                label = { Text("Text Input") }
            )
            Button(
                onClick = { displayedText = textEntered }) {
                Text(
                    text = "Go",
                    textAlign = TextAlign.Left,
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "Text Entered: $displayedText",
            textAlign = TextAlign.Left,
        )
    }
}