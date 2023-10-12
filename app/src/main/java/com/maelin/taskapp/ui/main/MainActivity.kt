package com.maelin.taskapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maelin.taskapp.ui.theme.TaskAppTheme
import com.maelin.taskapp.ui.theme.Typography
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {
                        Greeting()
                        Spacer(modifier = Modifier.weight(1f))
                        IncreaseButton()
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<MainViewModel>()
    Text(
        text = "Hello ${viewModel.itemsCount()}!",
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = Typography.bodyLarge
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val viewModel = hiltViewModel<MainViewModel>()a
    TaskAppTheme {
        Column {
            Greeting()
            IntegerList()
            Spacer(modifier = Modifier.weight(1f))
            IncreaseButton()
        }

    }
}

@Composable
fun IncreaseButton() {
    val viewModel = hiltViewModel<MainViewModel>()
    Button(onClick = {
                     viewModel.increaseItems()
    }, modifier = Modifier.fillMaxWidth()) {
        Text("Add new row")
    }
}

@Composable
fun IntegerList() {
    val viewModel = hiltViewModel<MainViewModel>()
    LazyColumn (
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(viewModel.getItems()) {
            item -> ListRow(content = item)
        }
    }
}

@Composable
fun ListRow(content: Int) {
    var expanded by remember { mutableStateOf(false) }
    Column (modifier = Modifier.clickable { expanded = !expanded }) {
        Row {
            // basic info about task
            Text(
                modifier = Modifier
                    .padding(PaddingValues(10.dp)),
                text = "Item number $content"
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = android.R.drawable.ic_delete),
                contentDescription = null,
                modifier = Modifier.padding(PaddingValues(10.dp)))
        }
        // expanded shows task points
        AnimatedVisibility(visible = expanded) {
            Text (
                text = "This is item number $content",
                modifier = Modifier.padding(10.dp)
            )
            
        }
        Divider(color = Color.Black, thickness = 0.5.dp)
    }
}