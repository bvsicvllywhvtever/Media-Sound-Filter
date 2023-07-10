package com.example.mediasoundfilter.youtube

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayerComposeView(videoId: String){
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val youTubePlayerView = remember{YouTubePlayerView(context)}

    youTubePlayerView.enableAutomaticInitialization = false
    youTubePlayerView.addFullscreenListener(object : FullscreenListener{
        override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {}
        override fun onExitFullscreen() {}
    })

    val iFramePlayerOptions: IFramePlayerOptions = IFramePlayerOptions.Builder()
        .controls(1)
        .fullscreen(1)
        .build()

    AndroidView({youTubePlayerView}) {view ->
        //loads video once player is ready
        view.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer){
                youTubePlayer.loadVideo(videoId, 0F)
            }
        }, true, iFramePlayerOptions)
    }

    //since youTubePlayerView is a view, we need to make sure it is created and destroyed with
    //its parent activity
    //that is done here
    DisposableEffect(lifecycleOwner){
        lifecycleOwner.lifecycle.addObserver(youTubePlayerView)
        onDispose { lifecycleOwner.lifecycle.removeObserver(youTubePlayerView) }
    }
}