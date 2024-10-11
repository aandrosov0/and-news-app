package dvx.news.app.viewModels

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dvx.news.app.states.AudioSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AudioViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _state = MutableStateFlow(AudioSource())
    val state = _state.asStateFlow()

    private lateinit var player: MediaPlayer

    private var playingJob: Job? = null

    fun play(url: String = "") {
        playingJob?.cancel()
        playingJob = viewModelScope.launch(dispatcher) {
            if (url.isNotBlank()) {
                player = MediaPlayer().apply {
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
            player.start()
        }
    }

    fun pause() = player.pause()

    fun setPosition(position: Int) {
        player.seekTo(position)
    }

    fun fetchSource() {
        viewModelScope.launch(dispatcher) {
            if (::player.isInitialized) {
                _state.value = AudioSource(
                    duration = player.duration,
                    position = player.currentPosition,
                    playing = player.isPlaying
                )
            }
        }
    }
}