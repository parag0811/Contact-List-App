package parag.ui.contactlistapp

import android.app.Application

class MyContactsApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this) //This: class it the context in which you will do whole database stuffs --> Location where all things should be setup
    }
}

//To ensure the project is aware of the application class Register with android manifest.