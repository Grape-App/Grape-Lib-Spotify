package rocks.grape.lib.spotify.models

import play.api.libs.json._

case class Artist(
    id: String,
    name: String,
    popularity: Option[Int],
    uri: String,
    href: String,
    genres: Option[Seq[String]],
    images: Option[Seq[Image]],
    external_urls: Option[Map[String, String]]
)

object Artist {
  implicit val format: Format[Artist] = Json.format[Artist]
  val readsSeq: Reads[Seq[Artist]] = new Reads[Seq[Artist]] {
    def reads(json: JsValue): JsResult[Seq[Artist]] = JsSuccess((json \ "artists").as[Seq[Artist]])
  }
}
