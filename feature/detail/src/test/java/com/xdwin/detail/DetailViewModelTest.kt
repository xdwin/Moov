package com.xdwin.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.runner.AndroidJUnit4
import com.xdwin.data.api.BaseResult
import com.xdwin.data.data.Credits
import com.xdwin.data.data.MovieDetail
import com.xdwin.detail.detail.DetailRepository
import com.xdwin.detail.detail.DetailViewModel
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val fakeSuccessDetailRepository = object : DetailRepository {
        override suspend fun getMovieDetail(movieId: Int): Response<MovieDetail> {
            return Response.success(200, MovieDetail(id = 1))
        }

        override suspend fun getCredits(movieId: Int): Response<Credits> {
            return Response.success(200, Credits(id = 2))
        }
    }

    private val fakeErrorDetailRepository = object : DetailRepository {
        override suspend fun getMovieDetail(movieId: Int): Response<MovieDetail> {
            return Response.error(500, ResponseBody.create(MediaType.parse(""), ""))
        }

        override suspend fun getCredits(movieId: Int): Response<Credits> {
            return Response.error(500, ResponseBody.create(MediaType.parse(""), ""))
        }
    }

    private fun setupDependencies(fakeRepository: DetailRepository) {
        detailViewModel = spyk(DetailViewModel(fakeRepository))
    }

    @Test
    fun `fetchPopularMovies success scenario`() {
        //given
        setupDependencies(fakeSuccessDetailRepository)
        val slot = slot<BaseResult<MovieDetail>>()
        val observer: Observer<BaseResult<MovieDetail>> = spyk()
        detailViewModel.detailMovie.observeForever(observer)

        // when
        detailViewModel.fetchPopularMovies(1)

        // then
        // exactly == 2, to prevent the intermediate state (loading state) to be captured
        verify(exactly = 2) { observer.onChanged(capture(slot)) }
        assertTrue(slot.captured is BaseResult.Success)
        detailViewModel.detailMovie.removeObserver(observer)
    }

    @Test
    fun `fetchPopularMovies error scenario`() {
        //given
        setupDependencies(fakeErrorDetailRepository)
        val slot = slot<BaseResult<MovieDetail>>()
        val observer: Observer<BaseResult<MovieDetail>> = spyk()
        detailViewModel.detailMovie.observeForever(observer)

        // when
        detailViewModel.fetchPopularMovies(1)

        // then
        verify { observer.onChanged(capture(slot)) }
        assertTrue(slot.captured is BaseResult.Error)
        detailViewModel.detailMovie.removeObserver(observer)
    }

    @Test
    fun `fetchCredits success scenario`() {
        //given
        setupDependencies(fakeSuccessDetailRepository)
        val slot = slot<BaseResult<Credits>>()
        val observer: Observer<BaseResult<Credits>> = spyk()
        detailViewModel.credits.observeForever(observer)

        // when
        detailViewModel.fetchCredits(1)

        // then
        verify { observer.onChanged(capture(slot)) }
        assertTrue(slot.captured is BaseResult.Success)
        detailViewModel.credits.removeObserver(observer)
    }

    @Test
    fun `fetchCredits error scenario`() {
        //given
        setupDependencies(fakeErrorDetailRepository)
        val slot = slot<BaseResult<Credits>>()
        val observer: Observer<BaseResult<Credits>> = spyk()
        detailViewModel.credits.observeForever(observer)

        // when
        detailViewModel.fetchCredits(1)

        // then
        verify { observer.onChanged(capture(slot)) }
        assertTrue(slot.captured is BaseResult.Error)
        detailViewModel.credits.removeObserver(observer)
    }
}