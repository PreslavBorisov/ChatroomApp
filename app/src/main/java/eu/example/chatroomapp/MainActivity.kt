package eu.example.chatroomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import data.AuthViewModel
import eu.example.chatroomapp.ui.theme.ChatRoomAppTheme
import screen.ChatRoomListScreen
import screen.ChatScreen
import screen.LoadingScreen
import screen.Screen
import screen.SingUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel()
            ChatRoomAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph(
                        navController = navController,
                        authViewModel = authViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
){
    NavHost(navController = navController, startDestination = Screen.SignUp.route){
        composable(Screen.SignUp.route){
            SingUpScreen(onNavigateToLogin = {
                navController.navigate(Screen.LoginScreen.route)
            }, authViewModel =authViewModel )
        }
        composable(Screen.LoginScreen.route){
            LoadingScreen(
                onNavigateToSignUp = {navController.navigate(Screen.SignUp.route)},
                authViewModel = authViewModel
            ){
                navController.navigate(Screen.ChatRooms.route)
            }
        }
        composable(Screen.ChatRooms.route){
            ChatRoomListScreen{
                navController.navigate("${Screen.ChatScreen.route}/${it.id}")
            }
        }
        composable("${Screen.ChatScreen.route}/{roomId}"){
            val roomId:String = it.arguments?.getString("roomId")?:""
            ChatScreen(roomId = roomId)
        }
    }
}