package dvx.news.app.states

data class AudioTrack(
    val duration: Int = 0,
    val playbackSpeed: Float = 1f,
    val currentPosition: Int = 0,
    val isPlaying: Boolean = false,
    val isLoading: Boolean = false,
    val onChangePosition: (Int) -> Unit = {},
    val onPlaybackSpeedChange: () -> Unit = {}
)
