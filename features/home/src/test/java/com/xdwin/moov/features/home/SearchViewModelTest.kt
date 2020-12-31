package com.xdwin.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.runner.AndroidJUnit4
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Movie
import com.xdwin.data.data.Movies
import com.xdwin.moov.features.home.search.SearchRepository
import com.xdwin.moov.features.home.search.SearchViewModel
import com.xdwin.moov.features.home.search.SearchViewModel.Companion.FIRST_PAGE
import io.mockk.slot
import io.mockk.spyk
import io.mockk.unmockkAll
import io.mockk.verify
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class SearchViewModelTest {
    private lateinit var searchViewModel: SearchViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val fakeSuccessSearchRepository = object : SearchRepository {
        override suspend fun searchMovies(query: String, page: Int): Response<Movies> {
            return Response.success(200, Movies(results = listOf(Movie(), Movie())))
        }
    }

    private val fakeErrorSearchRepository = object : SearchRepository {
        override suspend fun searchMovies(query: String, page: Int): Response<Movies> {
            return Response.error(500, ResponseBody.create(MediaType.parse(""), ""))
        }
    }

    private fun setupDependencies(searchRepository: SearchRepository) {
        searchViewModel = spyk(SearchViewModel(searchRepository))
    }

    @Test
    fun `query will be changed after onFirstSearch`() {
        // given
        setupDependencies(fakeSuccessSearchRepository)
        val fakeQuery = "About Time"

        // when
        searchViewModel.onFirstSearch(fakeQuery)

        // then
        assertTrue(searchViewModel.page == FIRST_PAGE)
    }

    @Test
    fun `onFirstSearch with a blank query will returns an empty list inside a movie`() {
        // given
        setupDependencies(fakeSuccessSearchRepository)

        val slot = slot<BaseResult<Movies>>()
        val observer: Observer<BaseResult<Movies>> = spyk()
        searchViewModel.searchResult.observeForever(observer)

        // when
        searchViewModel.onFirstSearch("")

        // then
        verify { observer.onChanged(capture(slot)) }
        assertTrue(slot.captured is BaseResult.Success)
        assertTrue((slot.captured as BaseResult.Success).data?.results?.size == 0)
        assertTrue(searchViewModel.page == 1)
        searchViewModel.searchResult.removeObserver(observer)
    }

    @Test
    fun `onFirstSearch with a non-blank query will returns a list of movie`() {
        // given
        setupDependencies(fakeSuccessSearchRepository)

        val slot = slot<BaseResult<Movies>>()
        val observer: Observer<BaseResult<Movies>> = spyk()
        searchViewModel.searchResult.observeForever(observer)

        // when
        searchViewModel.onFirstSearch("query")

        // then
        verify { observer.onChanged(capture(slot)) }
        val captured = slot.captured
        assertTrue(captured is BaseResult.Success || captured is BaseResult.Loading)
        searchViewModel.searchResult.removeObserver(observer)
    }

    @Test
    fun `onFirstSearch error scenario`() {
        // given
        setupDependencies(fakeErrorSearchRepository)

        val slot = slot<BaseResult<Movies>>()
        val observer: Observer<BaseResult<Movies>> = spyk()
        searchViewModel.searchResult.observeForever(observer)

        // when
        searchViewModel.onFirstSearch("query")

        // then
        verify { observer.onChanged(capture(slot)) }
        assertTrue(slot.captured !is BaseResult.Success)
        searchViewModel.searchResult.removeObserver(observer)
    }

    @After
    fun after() {
        unmockkAll()
    }
}