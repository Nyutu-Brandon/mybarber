package com.example.mybarber.data

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.mybarber.models.Client
import com.example.mybarber.navigation.ROUTE_HOME_PAGE
import com.example.mybarber.navigation.ROUTE_VIEW_CLIENT
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ClientViewModel(): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage:LiveData<String> get() = _errorMessage

    private val _successMessage = MutableLiveData<String>()
    val successMessage:LiveData<String> get() = _successMessage



    fun saveClient(firstname: String, lastname: String, time: String, date:String, navController: NavController,
                   context: Context
    ) {
        if (firstname.isBlank() || lastname.isBlank() || time.isBlank() ||
            date.isBlank() ){
            showToast("Please fill all the fields",context)
            return
        }
        val id = System.currentTimeMillis().toString()
        val dbRef = FirebaseDatabase.getInstance().getReference("SESSIONS/$id")

        val clientData = Client("", firstname, lastname, time, date, id)

        dbRef.setValue(clientData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Session booked successfully", context)
                    navController.navigate(ROUTE_HOME_PAGE)

                } else {
                    showToast("Session not booked successfully", context)
                }
            }
    }
    fun viewClients(client: MutableState<Client>,
                    clients:SnapshotStateList<Client>,context: Context):
            SnapshotStateList<Client>{
        val ref  = FirebaseDatabase.getInstance().getReference()
            .child("SESSIONS")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                clients.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Client::class.java)
                    client.value = value!!
                    clients.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                showToast("Failed to fetch clients", context)
            }
        })
        return clients
    }
    fun updateClient(context: Context,navController: NavController,
                     firstname: String,lastname: String,
                     time: String,date: String,id: String){
        val databaseReference = FirebaseDatabase.getInstance()
            .getReference("SESSIONS/$id")
        val updatedClient = Client("",firstname, lastname,
            time, date,id)

        databaseReference.setValue(updatedClient)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    showToast("Reservation Updated Successfully",context)
                    navController.navigate(ROUTE_VIEW_CLIENT)
                }else{
                    showToast("Record update failed", context)
                }
            }
    }
    fun deleteClient(context: Context,id: String,
                     navController: NavController){
        AlertDialog.Builder(context)
            .setTitle("Delete Session")
            .setMessage("Are you sure you want to delete this session?")
            .setPositiveButton("Yes"){ _, _ ->
                val databaseReference = FirebaseDatabase.getInstance()
                    .getReference("SESSIONS/$id")
                databaseReference.removeValue().addOnCompleteListener {
                        task ->
                    if (task.isSuccessful){
                        showToast("Session deleted Successfully",context)
                    }else{
                        showToast("Session not deleted",context)
                    }
                }
            }
            .setNegativeButton("No"){ dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }



    public fun showToast(message: String, context:Context){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

}
