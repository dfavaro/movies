package com.danielefavaro.movies.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.danielefavaro.movies.data.database.MainDatabase
import com.danielefavaro.movies.main.di.DaggerMainComponentTest
import com.danielefavaro.movies.main.di.MainComponentTest
import com.danielefavaro.movies.main.domain.MoviesRepository
import com.danielefavaro.movies.main.ui.viewmodel.MainViewModel
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import javax.inject.Inject

@ExperimentalCoroutinesApi
open class BaseUnitTest {

    val component: MainComponentTest = DaggerMainComponentTest.create()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = TestCoroutineRule()

    @Inject
    lateinit var repository: MoviesRepository

    @Inject
    lateinit var database: MainDatabase

    @Inject
    lateinit var mockWebServer: MockWebServer

    lateinit var mainViewModel: MainViewModel

    @Before
    open fun setUp() {
        component.inject(this)

        mockStuff()

        mainViewModel = MainViewModel(repository)
    }

    @After
    open fun tearDown() {
        database.close()
        mockWebServer.shutdown()
    }

    private fun mockStuff() {
        // TODO
    }
}