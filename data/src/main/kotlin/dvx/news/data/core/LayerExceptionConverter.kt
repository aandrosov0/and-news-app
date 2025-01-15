package dvx.news.data.core

internal interface LayerExceptionConverter {
    fun convert(exception: Exception): Exception
}