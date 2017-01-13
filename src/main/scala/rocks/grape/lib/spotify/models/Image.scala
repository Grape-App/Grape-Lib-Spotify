package rocks.grape.lib.spotify.models

import play.api.libs.json.{Format, Json}

case class Image(
    height: Long,
    width: Long,
    url: String
)

object Image {
  implicit val format: Format[Image] = Json.format[Image]
}
