package commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.coobird.thumbnailator.Thumbnails
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.net.URL

class Image : Command() {

    init {
        this.name = "image"
        this.arguments = "[width] [height] [image url] [degrees to rotate(optional)]"
        this.help = "manipulates images. Provide an image link and you can resize and/or rotate it"
    }

    override fun execute(event: CommandEvent?) {
        event?.reply("image command yes")

        if(event?.args == "") {
            event.reply("Please provide arguments to work properly. " + event.author.asMention)
            event.reply("type the command like this : ")
            event.reply(";;image [width] [height] [image url] [degrees to rotate(optional)]")
        } else {
            val args = event?.args?.split(" ")
            val width = args?.get(0)?.toInt()
            val height = args?.get(1)?.toInt()
            val url = URL(args?.get(2))
            var rotation = 0

            if(args?.size == 4) {
                rotation = args?.get(3).toInt()
            }

            if (width != null) {
                if (height != null) {
                    val os = ByteArrayOutputStream()
                    Thumbnails.of(url).forceSize(width, height).rotate(rotation.toDouble()).outputFormat("png").toOutputStream(os)

                    val imageBytes = os.toByteArray()

                    event.channel.sendFile(imageBytes, "converted image.png").queue()
                    event.reply("enjoy your image result")
                }
            }

        }
    }
}