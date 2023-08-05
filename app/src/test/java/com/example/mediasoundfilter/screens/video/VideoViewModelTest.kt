package com.example.mediasoundfilter.screens.video

import com.example.mediasoundfilter.domain.model.Video
import com.example.mediasoundfilter.fake.VideoRepositoryFake
import com.example.mediasoundfilter.ui.screens.video.VideoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class VideoViewModelTest {

    private lateinit var videoViewModel: VideoViewModel

    var videoRepository =  VideoRepositoryFake()
    @Before
    fun setUp(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `Test valid youtube link returns video `() = runTest{
        val validString = "https://www.youtube.com/watch?v=ErhtJdsIJdM"
        val expectedVideoResult = Video("123","test")
        videoViewModel = VideoViewModel(videoRepository)
        videoViewModel.extractVideoId(validString)
        val videoUiState = videoViewModel.videoUiState.value
        Assert.assertEquals(videoUiState.videoTitle,expectedVideoResult.title)
    }

    @Test
    fun `Test null youtube link returns error state`() = runTest{
        videoViewModel = VideoViewModel(videoRepository)
        videoViewModel.extractVideoId("")
        val videoUiState = videoViewModel.videoUiState.value
        Assert.assertEquals(videoUiState.linkError,"Link field cannot be left empty.")
    }

    @Test
    fun `Test invalid youtube link returns error state`() = runTest{
        val invalidString = "https://www.youtube.com"
        videoViewModel = VideoViewModel(videoRepository)
        videoViewModel.extractVideoId(invalidString)
        val videoUiState = videoViewModel.videoUiState.value
        Assert.assertEquals(videoUiState.linkError,"Youtube link is not valid.")
    }

}