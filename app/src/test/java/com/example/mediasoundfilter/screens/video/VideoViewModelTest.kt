package com.example.mediasoundfilter.screens.video

import com.example.mediasoundfilter.domain.VideoRepository
import com.example.mediasoundfilter.domain.model.Video
import com.example.mediasoundfilter.fake.VideoRepositoryFake
import com.example.mediasoundfilter.ui.screens.video.VideoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class VideoViewModelTest {

    private lateinit var videoViewModel: VideoViewModel

    var videoRepository =  VideoRepositoryFake()

    val validString = "test"

    @Before
    fun setUp(){
        //initializes mockito annotations before the rest of the class methods are called
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // view model returns the right state after repository returns 3 different ui states

    @Test
    fun `Test valid youtube link returns video `() = runTest{
        val validString = "https://www.youtube.com/watch?v=ErhtJdsIJdM"
        val expectedVideoResult = Video("123","test")
        videoViewModel = VideoViewModel(videoRepository)
        videoViewModel.extractVideoId(validString)

        val videoUiState = videoViewModel.videoUiState.value

        Assert.assertEquals(videoUiState.videoTitle,expectedVideoResult.title)

    }



}