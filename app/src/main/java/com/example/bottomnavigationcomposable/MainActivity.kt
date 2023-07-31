package com.example.bottomnavigationcomposable

import androidx.compose.runtime.*
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
//import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.bottomnavigationcomposable.ui.theme.BottomNavigationComposableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationComposableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyUI()
                }
            }
        }
    }
}

@Composable
fun MyUI() {
    // Bottom Navigation is used to display the destination
    // at the bottom of the screen.

    // Types of Bottom Navigation
        // Bottom Navigation
        // Bottom App Bar

    // Items List
    val bottomMenuItemList = prepareBottomMenu()

    val context = LocalContext.current.applicationContext

    var selectedItem by remember{
        mutableStateOf("Home")
    }

    Surface() {
        Box(modifier = Modifier.fillMaxSize()) {
            NavigationBar(modifier = Modifier.align(alignment = Alignment.BottomCenter)) {

                bottomMenuItemList.forEach {
                    menuItem ->
                    NavigationBarItem(
                        selected =(selectedItem == menuItem.label) ,
                        onClick = {
                            selectedItem = menuItem.label

                            Toast.makeText(context, menuItem.label, Toast.LENGTH_SHORT).show()
                        },
                    icon = {
                        Icon(imageVector = menuItem.icon,
                            contentDescription = menuItem.label )
                    },
                     label = {
                         Text(text = menuItem.label)
                     },
                     enabled = true
                        )
                }

            }
        }
    }


}

// Perpare the menu items
private fun prepareBottomMenu(): List<BottomMenuItem>{
    val bottomMenuItemsList = arrayListOf<BottomMenuItem>()

    // adding menu items
    bottomMenuItemsList.add(BottomMenuItem("Home", Icons.Filled.Home))
    bottomMenuItemsList.add(BottomMenuItem("Profile",Icons.Filled.Person))
    bottomMenuItemsList.add(BottomMenuItem("Card",Icons.Filled.ShoppingCart))
    bottomMenuItemsList.add(BottomMenuItem("Settings",Icons.Filled.Settings))

    return bottomMenuItemsList
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomNavigationComposableTheme {
        MyUI()
    }
}