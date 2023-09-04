package com.curso.android.app.practica.proyectofinal.view

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import com.curso.android.app.practica.proyectofinal.R
import kotlinx.coroutines.launch

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {



    @get:Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(MainActivity::class.java)


    private lateinit var context: Context


    @Before
    fun setup() {
        // Configura un contexto simulado utilizando ApplicationProvider
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun mainActivity_compararTextoInicialNulo(){
        Espresso.onView(
            ViewMatchers.withId(R.id.botoncomparar)
        ).perform(
            ViewActions.click()
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.resultado)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(context.getString(R.string.comparacion_nula))
            )
        )
    }


}