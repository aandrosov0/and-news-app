package dvx.news.data.core

import com.dvxnews.api.exceptions.DVXConnectionException
import com.dvxnews.api.exceptions.DVXException
import dvx.news.data.exceptions.DataLayerConnectionException
import dvx.news.data.exceptions.DataLayerGenericException

internal object LayerExceptionConverterImpl : LayerExceptionConverter {
    override fun convert(exception: Exception) = when (exception) {
        is DVXException -> DataLayerGenericException(exception)
        is DVXConnectionException -> DataLayerConnectionException(exception)
        else -> exception
    }
}