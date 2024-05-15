package com.example.moneymanager20logic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.moneymanager20logic.Database.Database
import com.example.moneymanager20logic.Database.presetation.VMSaving
import com.example.moneymanager20logic.ui.theme.MoneyManager20LogicTheme

class MainActivity : ComponentActivity() {
    private  val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            name="database.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    private val viewModel by  viewModels<VMSaving>(
        factoryProducer = {
            object :ViewModelProvider.Factory{
                override fun <T: ViewModel> create(modelClass: Class<T>):T{
                    return VMSaving(database.savingDao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyManager20LogicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    val state by viewModel.state.collectAsState()
                    val sum by viewModel.sum.collectAsState()
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination ="HomeScreen"){
                        composable(route="HomeScreen"){
                            HomeScreen(
                                state = state,
                                navController =navController,
                                onEvent =viewModel::onEvent ,
                                sum=sum)
                        }
//                        composable(route="AddScreen"){
//                            AddScreen(
//                                state = state,
//                                navController =navController ,
//                                onEvent =viewModel::onEvent
//                                )
//                        }

                    }

                }
            }
        }
    }
}

