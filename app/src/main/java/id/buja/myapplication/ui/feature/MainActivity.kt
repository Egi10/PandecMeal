package id.buja.myapplication.ui.feature

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import id.buja.myapplication.domain.model.Meals
import id.buja.myapplication.ui.feature.model.MainEventState
import id.buja.myapplication.ui.feature.model.MainUiState
import id.buja.myapplication.ui.theme.MyApplicationTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainRoute()
                }
            }
        }
    }
}

@Composable
fun MainRoute(
    viewModel: MainViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val context = LocalContext.current

    MainScreen(
        uiState = uiState.value,
        value = viewModel.search,
        onValueChange = {
            viewModel.onEvent(
                event = MainEventState.SearchOnChange(it)
            )
        },
        onClickSearch = {
            viewModel.onEvent(
                event = MainEventState.Search
            )
        },
        onClickItem = {
            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun MainScreen(
    uiState: MainUiState,
    value: String,
    onValueChange: (String) -> Unit,
    onClickSearch: () -> Unit,
    onClickItem: (Meals) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                all = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Search(
            value = value,
            onValueChange = onValueChange,
            onClick = onClickSearch
        )

        Spacer(
            modifier = Modifier
                .padding(
                    top = 16.dp
                )
        )

        when(uiState) {
            MainUiState.Loading -> {
                CircularProgressIndicator()
            }

            is MainUiState.Success -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    content = {
                        items(
                            uiState.data,
                            key = {
                                it.id
                            }
                        ) {
                            ItemList(
                                name = it.name,
                                image = it.image,
                                onClick = {
                                    onClickItem.invoke(it)
                                }
                            )
                        }
                    }
                )
            }

            is MainUiState.Error -> {
                Text(text = uiState.error)
            }

            MainUiState.Empty -> {
                Text(text = "Data Kosong Ni")
            }

            MainUiState.Idle -> {
                Text(text = "Ayok Lakukan Pencarian")
            }
        }

    }
}

@Composable
fun Search(
    value: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            placeholder = {
                Text(text = "Search")
            }
        )

        Spacer(
            modifier = Modifier
                .padding(
                    start = 16.dp
                )
        )

        Button(onClick = onClick) {
            Text(text = "Search")
        }
    }
}

@Composable
fun ItemList(
    name: String,
    image: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable {
                onClick.invoke()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = image,
            contentDescription = name,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(
            modifier = Modifier
                .padding(
                    start = 16.dp
                )
        )

        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun ItemListPreview() {
   ItemList(name = "Egi", image = "", onClick = {  })
}