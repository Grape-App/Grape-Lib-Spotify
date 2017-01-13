package rocks.grape.lib.spotify.utils

import com.google.inject.Inject
import play.api.Logger
import play.api.libs.json.Reads
import rocks.grape.lib.commons.ServiceResult.ServiceResult
import rocks.grape.lib.commons.{ServiceError, ServiceResult}

import scala.concurrent.ExecutionContext

class WSLoggingClient @Inject()(
    ws: WSClient)(
    implicit ec: ExecutionContext) {

  private val logger: Logger = Logger(this.getClass)

  def get[T](url: String)(implicit reads: Reads[T]): ServiceResult[T] = ServiceResult {
    logger.debug(s"Getting json from url: $url")
    val time = System.nanoTime()
    ws
      .url(url)
      .get()
      .map(r => r.json.validate[T](reads).fold(
        invalid => {
          logger.debug(s"Failed to get json after ${System.nanoTime() - time}ns from url: $url - ${invalid.toString}")
          Left(ServiceError(s"getArtistRelatedArtists returned invalid json $invalid ${r.json.toString}"))
        },
        valid => {
          logger.debug(s"Retuned after ${System.nanoTime() - time}ns value: $valid from url: $url")
          Right(valid)
        }
      ))
  }
}
