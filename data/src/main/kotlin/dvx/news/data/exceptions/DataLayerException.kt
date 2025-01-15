package dvx.news.data.exceptions

sealed class DataLayerException : RuntimeException {
    constructor(throwable: Throwable) : super(throwable)
}