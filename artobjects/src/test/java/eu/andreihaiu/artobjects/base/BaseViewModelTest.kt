package eu.andreihaiu.artobjects.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseViewModelTest {
    @get:Rule
    open val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    open val testCoroutineRule = CoroutineTestRule()

    @MockK
    protected lateinit var owner: LifecycleOwner

    private lateinit var lifecycle: LifecycleRegistry

    @Before
    open fun setup() {
        MockKAnnotations.init(this)
        lifecycle = LifecycleRegistry(owner)
        every { owner.lifecycle } returns lifecycle
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}