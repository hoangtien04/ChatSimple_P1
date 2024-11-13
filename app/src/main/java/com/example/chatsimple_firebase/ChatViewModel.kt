package com.example.chatsimple_firebase

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

class ChatViewModel(email:String):ViewModel(){
    var state by mutableStateOf(ChatState())
        private set
    init{
        if(!email.isNullOrEmpty()){
            viewModelScope.launch {
                Firebase.firestore.collection("users")
                    .whereNotEqualTo("email",email)
                    .addSnapshotListener{value,error ->
                        if(value != null){
                            for(doc in value){
                                state = state.copy(
                                    friend = doc.toObject(User::class.java)
                                )
                                break;
                            }
                        }
                    }
            }
            getMessages()
        }
    }
    fun getMessages(){
        viewModelScope.launch {
            Firebase.firestore.collection("messages")
                .addSnapshotListener{value,error->
                    Log.d("ABC", state.friend.email)
                    var list = mutableListOf<Messsage>()
                    if(value!=null){
                            for (doc in value){
                                var mess = doc.toObject(Messsage::class.java)
                                if((mess.sender == FirebaseAuth.getInstance().currentUser?.email
                                            && mess.receiver==state.friend.email)
                                    || (mess.sender == state.friend.email
                                            && mess.receiver == FirebaseAuth.getInstance().currentUser?.email)){
                                    list.add(mess)
                                }
                            }
                        state = state.copy(
                            message = list.sortedBy { it.timeline }.asReversed()
                        )
                    }
                }
        }
    }

    fun addMessage(){
        val message = Messsage(
            FirebaseAuth.getInstance().currentUser?.email.toString(),
            state.friend.email, state.mess, System.currentTimeMillis()
        )
        Firebase.firestore.collection("messages")
            .add(message).addOnSuccessListener {
                viewModelScope.launch {
                    state = state.copy(mess = "")
                }
            }
    }
    fun onChangeMessage(newValue:String){
        state = state.copy(mess = newValue)
    }
}

data class ChatState(
    val friend:User = User(),
    val message:List<Messsage> = emptyList(),
    val mess:String = "",
)

@Suppress("UNCHECKED_CAST")
class ChatViewModelFactory(private  val email:String):ViewModelProvider.Factory{
    override fun<T:ViewModel> create(modelClass:Class<T>):T{
        return ChatViewModel(email) as T
    }
}

