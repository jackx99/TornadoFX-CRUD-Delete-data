package com.example.demo.view.crud

import com.example.demo.controller.HyperlinkController
import com.example.demo.model.Hyperlink
import com.example.demo.utilities.PopupDialog
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.Parent
import javafx.stage.StageStyle
import tornadofx.*

class DeleteFragment : Fragment("DELETE") {
    private val comboboxObject = SimpleObjectProperty<Hyperlink>()
    private val hyperlinkController: HyperlinkController by inject()
    override val root = vbox {
        form {
            combobox<Hyperlink>(comboboxObject) {
                items = hyperlinkController.hyperlinks
                cellFormat {
                    text = this.item.title
                }
            }
            button("Delete Hyperlink") {
                action {
                    hyperlinkController.deleteHyperlink(comboboxObject.get())
                    comboboxObject.value = null
                    find<PopupDialog>(mapOf("message" to "Hyperlink Deleted")).openModal(stageStyle =  StageStyle.UTILITY)
                }
            }
        }
    }
}