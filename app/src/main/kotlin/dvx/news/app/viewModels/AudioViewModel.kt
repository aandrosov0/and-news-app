package dvx.news.app.viewModels

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.states.AudioTrack
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AudioViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private lateinit var mediaPlayer: MediaPlayer
    private val _uiState = MutableStateFlow(AudioTrack())

    private var updatingJob: Job? = null

    val uiState = _uiState.asStateFlow()

    fun start(url: String) {
        viewModelScope.launch(dispatcher) {
            _uiState.value = AudioTrack(isLoading = true)
            if (!::mediaPlayer.isInitialized) {
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(url)
                    prepare()
                }
            }

            mediaPlayer.start()
            updatingJob?.cancel()
            updatingJob = launch { updateStateEveryTime() }
        }
    }

    fun pause() {
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.pause()
            updateState()
        }
    }

    override fun onCleared() {
        updatingJob?.cancel()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
        super.onCleared()
    }

    private fun updateState() {
        _uiState.value = AudioTrack(
            duration = mediaPlayer.duration,
            playbackSpeed = mediaPlayer.playbackParams.speed.coerceAtLeast(1f),
            currentPosition = mediaPlayer.currentPosition,
            isPlaying = mediaPlayer.isPlaying,
            onChangePosition = {
                mediaPlayer.seekTo(it)
                updateState()
            },
            onPlaybackSpeedChange = {
                val speed = mediaPlayer.playbackParams.speed.coerceAtLeast(1f)
                mediaPlayer.playbackParams = mediaPlayer.playbackParams.setSpeed(speed + 0.5f)
                if (mediaPlayer.playbackParams.speed > 2f) {
                    mediaPlayer.playbackParams = mediaPlayer.playbackParams.setSpeed(1f)
                }
                updateState()
            }
        )
    }

    private suspend fun updateStateEveryTime() {
        while (true) {
            updateState()
            delay(100)
        }
    }
}