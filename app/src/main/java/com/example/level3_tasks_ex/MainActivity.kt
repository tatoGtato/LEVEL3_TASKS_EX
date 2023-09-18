package com.example.level3_tasks_ex

import AddReminderScreen
import RemindersListScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.level3_tasks_ex.ui.theme.LEVEL3_TASKS_EXTheme
import com.example.level3_tasks_ex.ui.theme.screens.RemindersScreens

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LEVEL3_TASKS_EXTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    RemindersNavHost(navController = navController, modifier = Modifier)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
private fun RemindersNavHost(
    navController: NavHostController, modifier: Modifier
) {
    val viewModel: RemindersViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = RemindersScreens.RemindersList.name,
        modifier = modifier
    )
    {
        composable(RemindersScreens.RemindersList.name) {
            RemindersListScreen(navController, viewModel)
        }
        composable(RemindersScreens.NewReminder.name) {
            AddReminderScreen(navController, viewModel)
        }
    }
}
