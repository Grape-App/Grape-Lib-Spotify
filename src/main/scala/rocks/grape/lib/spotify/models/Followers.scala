package rocks.grape.lib.spotify.models

import play.api.libs.json.{Format, Json}

case class Followers(
    href: String,
    total: Long
)

object Followers {
  implicit val format: Format[Followers] = Json.format[Followers]
}
