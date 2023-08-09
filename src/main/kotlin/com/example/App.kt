package com.example

import io.kvision.*
import io.kvision.form.formPanel
import io.kvision.form.select.TomSelect
import io.kvision.form.select.TomSelectOptions
import io.kvision.html.br
import io.kvision.panel.root
import io.kvision.panel.simplePanel
import io.kvision.utils.px
import kotlinx.serialization.Serializable

class App : Application() {

    @Serializable
    data class SelectData(val field1: String, val field2: String, val field3: String)

    override fun start() {
        root("kvapp") {

            simplePanel {
                paddingTop = 30.px
                paddingLeft = 30.px

                val listItems = List(75) { "Item ${it + 1}" }.map { it to it }

                val select1 =
                    TomSelect(options = listItems) {
                        width = 300.px
                        label = "TomSelect with no selectSize value"
                        // selectSize = 5
                        multiple = false
                        tsOptions = TomSelectOptions(dropdownInput = true)
                        options = listItems
                    }

                val select2 =
                    TomSelect(options = listItems) {
                        width = 300.px
                        label = "TomSelect with selectSize = 5"
                        selectSize = 5
                        multiple = false
                        tsOptions = TomSelectOptions(dropdownInput = true)
                    }

                val select3 =
                    TomSelect(options = listItems) {
                        width = 300.px
                        label = "TomSelect with selectSize = 15"
                        selectSize = 15
                        multiple = false
                        tsOptions = TomSelectOptions(dropdownInput = true)
                    }

                formPanel {
                    width = 400.px
                    add(key = SelectData::field1, control = select1)
                    br {}
                    add(key = SelectData::field2, control = select2)
                    br {}
                    add(key = SelectData::field3, control = select3)
                }
            }

            br {}
        }
    }
}

fun main() {
    startApplication(
        ::App,
        module.hot,
        BootstrapModule,
        BootstrapCssModule,
        CoreModule,
        TomSelectModule,
    )
}
