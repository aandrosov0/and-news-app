package dvx.news.app.viewModels

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.states.Audio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AudioViewModel : ViewModel() {
    private var _state = MutableStateFlow(Audio.STOPPED)
    val state = _state.asStateFlow()

    private var mediaPlayer: MediaPlayer? = null

    private var startingJob: Job? = null
    private var continueJob: Job? = null

    val durationInMillis
        get() = mediaPlayer?.duration ?: 0

    val positionInMillis
        get() = mediaPlayer?.currentPosition ?: 0

    fun start(url: String) {
        startingJob?.cancel()
        if (mediaPlayer == null) {
            startingJob = viewModelScope.launch(Dispatchers.IO) {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(url)
                    prepare()
                    start()
                    _state.value = Audio.PLAYING
                }
            }
        }
    }

    fun continueAudio() {
        continueJob?.cancel()
        continueJob = viewModelScope.launch(Dispatchers.IO) {
            mediaPlayer?.prepare()
            mediaPlayer?.start()
            _state.value = Audio.PLAYING
        }
    }

    fun stop() {
        mediaPlayer?.stop()
        _state.value = Audio.STOPPED
    }
}