package screen

sealed class Screen (val route:String){
    object LoginScreen: Screen("loginscreen")
    object SignUp: Screen("signupscreen")
    object ChatRooms: Screen("chatroomscreen")
    object ChatScreen: Screen("chatScreen")
}