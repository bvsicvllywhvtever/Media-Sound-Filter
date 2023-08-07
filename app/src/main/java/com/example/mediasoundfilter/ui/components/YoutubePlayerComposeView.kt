package com.example.mediasoundfilter.ui.components

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayerComposeView(videoId: String, muteTimes: List<Pair<Double, Double>>){
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val youTubePlayerView = remember{YouTubePlayerView(context)}

    var isInitialized by remember { mutableStateOf(false) }
    var isMuted = false
    var isPlaying = false
    var idx = 0
    var start = -1.0
    var end = -1.0
    if(muteTimes.isNotEmpty()) {
        start = muteTimes.get(0).first
        end = muteTimes.get(0).second
    }

    youTubePlayerView.enableAutomaticInitialization = false
    youTubePlayerView.addFullscreenListener(object : FullscreenListener{
        override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {}
        override fun onExitFullscreen() {}
    })

    val iFramePlayerOptions: IFramePlayerOptions = IFramePlayerOptions.Builder()
        .controls(1)
        .fullscreen(1)
        .build()

    val initializer = remember {
        object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer){
                youTubePlayer.loadVideo(videoId, 0F)
            }

            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                isPlaying = state == PlayerConstants.PlayerState.PLAYING
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                if(isPlaying && muteTimes.isNotEmpty()) {
                    when {
                        second in start..end && !isMuted -> {
                            youTubePlayer.mute()
                            isMuted = true
                        }

                        second > end && isMuted -> {
                            youTubePlayer.unMute()
                            isMuted = false
                            if(idx < muteTimes.size - 1){
                                idx++
                                start = muteTimes.get(idx).first
                                end = muteTimes.get(idx).second
                            }
                        }
                    }
                }
            }

        }
    }

    AndroidView({youTubePlayerView}) {view ->
        //loads video once player is ready
        if(!isInitialized){
            isInitialized = true
            view.initialize(initializer, true, iFramePlayerOptions)
        }
    }

    //since youTubePlayerView is a view, we need to make sure it is created and destroyed with
    //its parent activity
    //that is done here
    DisposableEffect(lifecycleOwner){
        lifecycleOwner.lifecycle.addObserver(youTubePlayerView)
        onDispose { lifecycleOwner.lifecycle.removeObserver(youTubePlayerView) }
    }
}