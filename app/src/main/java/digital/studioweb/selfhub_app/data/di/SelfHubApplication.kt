package digital.studioweb.selfhub_app.data.di

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SelfHubApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

    }
}