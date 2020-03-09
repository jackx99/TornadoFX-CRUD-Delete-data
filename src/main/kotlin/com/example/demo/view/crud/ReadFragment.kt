package com.example.demo.view.crud

import com.example.demo.controller.HyperlinkController
import com.example.demo.model.Hyperlink
import tornadofx.*
import java.awt.Desktop
import java.net.URI

class ReadFragment : Fragment("READ") {
    private val hyperlinkController: HyperlinkController by inject()

    override val root = listview<Hyperlink> {
        items = hyperlinkController.hyperlinks
        cellFormat {
            text = this.item.title
            onDoubleClick {
                if (selectedItem != null) {
                    val uri = URI(it.url)
                    openWebpage(uri)
                }
            }
        }
    }

    private fun openWebpage(uri: URI) {
        val desktop = if (Desktop.isDesktopSupported()) Desktop.getDesktop() else null
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            desktop.browse(uri)
        }
    }
}