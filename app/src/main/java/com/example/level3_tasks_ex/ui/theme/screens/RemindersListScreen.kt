

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.level3_tasks_ex.R
import com.example.level3_tasks_ex.RemindersViewModel
import com.example.level3_tasks_ex.ui.theme.LEVEL3_TASKS_EXTheme
import com.example.level3_tasks_ex.ui.theme.screens.RemindersScreens

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersListScreen(navController: NavHostController, viewModel: RemindersViewModel) {
    val context = LocalContext.current
    val localDensity = LocalDensity.current
    var HeightDp by remember { mutableStateOf(0.dp) }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    HeightDp = with(localDensity) { coordinates.size.height.toDp() }},
                title = {
                    Text(
                        text = context.getString(R.string.app_name),
                    )
                },
            )
        },
        content = {innerPadding -> RemindersList(Modifier.padding(innerPadding), viewModel)

        },
        floatingActionButton = { RemindersListScreenFab(navController) }
    )
}

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun RemindersList(modifier: Modifier, viewModel: RemindersViewModel){
    Column(
        Modifier
            .fillMaxHeight()
            .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Headers()

        LazyColumn {
            items(
                items = viewModel.reminders,
                itemContent = { item ->
                    ReminderCard(item)
                }
            )
        }
    }
}

@Composable
private fun ReminderCard(item: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp),
    ) {
        Text(
            text = item,
            Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun Headers() {
    Text(
        text = stringResource(R.string.reminders),
        modifier = Modifier.padding(bottom = 8.dp),
        style = MaterialTheme.typography.headlineMedium,
    )
    Text(
        text = stringResource(R.string.see_below),
        modifier = Modifier.padding(
            bottom = 16.dp
        ),
        style = MaterialTheme.typography.headlineSmall,
    )
}

@Composable
fun RemindersListScreenFab(navController: NavHostController) {
    FloatingActionButton(onClick = {
        navController.navigate(RemindersScreens.NewReminder.name)
    }) {
        Icon(Icons.Filled.Add, "")
    }
}