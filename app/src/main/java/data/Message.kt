package data

data class Message(
    val text: String,
    val senderFirstName: String,
    val timestamp: Long, // or any other appropriate type
    val isSentByCurrentUser: Boolean,
    val senderId: String
)
