package dvx.news.app.states

data class AudioSource(
    val duration: Int = 0,
    val position: Int = 0,
    val playing: Boolean = false
)

enum class AudioSourceState {
    PLAYING,
    PAUSED,
}