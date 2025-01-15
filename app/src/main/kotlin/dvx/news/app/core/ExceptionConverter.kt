package dvx.news.app.core

fun interface ExceptionConverter<T> {
    fun convert(exception: Exception): T
}