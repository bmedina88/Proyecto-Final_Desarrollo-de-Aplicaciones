package com.curso.android.app.practica.proyectofinal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.curso.android.app.practica.proyectofinal.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModelUnitTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        viewModel= MainViewModel();
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_CheckInitialValue() = runTest {
        val text1 = viewModel.comparar.value?.text1
        val text2 = viewModel.comparar.value?.text2
        assertTrue(text1.isNullOrEmpty())
        assertTrue(text2.isNullOrEmpty())
    }
    @Test
    fun mainViewModel_VerificarActualizarTextos() = runTest {
        val exampleTest1: String = "Texto Prueba1"
        val exampleTest2: String = "Texto Prueba2"
        launch {
            viewModel.actualizarTextos(exampleTest1, exampleTest2)
        }
        advanceUntilIdle()
        assertEquals(exampleTest1, viewModel.comparar.value?.text1)
        assertEquals(exampleTest2, viewModel.comparar.value?.text2)

    }
    @Test
    fun mainViewModel_VerificarActualizarTextosDoble() = runTest {
        val exampleTest1: String = "Texto Prueba1"
        val exampleTest2: String = "Texto Prueba2"
        val secondExampleTest1= "Segundo texto Prueba1"
        val secondExampleTest2= "Segundo texto Prueba2"
        launch {
            viewModel.actualizarTextos(exampleTest1, exampleTest2)
            viewModel.actualizarTextos(secondExampleTest1, secondExampleTest2)
        }
        advanceUntilIdle()
        assertEquals(secondExampleTest1, viewModel.comparar.value?.text1)
        assertEquals(secondExampleTest2, viewModel.comparar.value?.text2)

    }

    @Test
    fun mainViewModel_VerificarCompararTextosIguales() = runTest {
        val exampleTest1: String = "Este texto es igual"
        val exampleTest2: String = "Este texto es igual"
        launch {
            viewModel.actualizarTextos(exampleTest1, exampleTest2)
        }
        advanceUntilIdle()
        assertTrue(viewModel.compararTextos())

    }
    @Test
    fun mainViewModel_VerificarCompararTextosDistintos() = runTest {
        val exampleTest1: String = "Este texto es igual"
        val exampleTest2: String = "Este texto no es igual"
        launch {
            viewModel.actualizarTextos(exampleTest1, exampleTest2)
        }
        advanceUntilIdle()
        assertFalse(viewModel.compararTextos())

    }

    @Test
    fun mainViewModel_VerificarTextosNulos() = runTest {
        val exampleTest1: String = ""
        val exampleTest2: String = ""
        launch {
            viewModel.actualizarTextos(exampleTest1, exampleTest2)
        }
        advanceUntilIdle()
        assertFalse(viewModel.sonTextosNoNulos())

    }
    @Test
    fun mainViewModel_VerificarTextosNoNulos() = runTest {
        val exampleTest1: String = "No nulo"
        val exampleTest2: String = "Tampoco"
        launch {
            viewModel.actualizarTextos(exampleTest1, exampleTest2)
        }
        advanceUntilIdle()
        assertTrue(viewModel.sonTextosNoNulos())

    }
    @Test
    fun mainViewModel_VerificarTextoNuloYNoNulo() = runTest {
        val exampleTest1: String = ""
        val exampleTest2: String = "No Nulo"
        launch {
            viewModel.actualizarTextos(exampleTest1, exampleTest2)
        }
        advanceUntilIdle()
        assertTrue(viewModel.sonTextosNoNulos())

    }
}