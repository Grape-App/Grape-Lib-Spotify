package rocks.grape.lib.spotify

import com.google.inject.AbstractModule
import rocks.grape.lib.spotify.service.public.{ArtistService, TrackService}
import rocks.grape.lib.spotify.utils.WSLoggingClient

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ArtistService]).asEagerSingleton()
    bind(classOf[TrackService]).asEagerSingleton()

    bind(classOf[WSLoggingClient]).asEagerSingleton()
  }
}
