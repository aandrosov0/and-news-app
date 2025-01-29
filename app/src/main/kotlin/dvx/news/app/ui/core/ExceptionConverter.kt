package dvx.news.app.ui.core

fun interface ExceptionConverter<T> {
    fun convert(exception: Exception): T
}