import kotlin.browser.document

import kotlinx.html.*
import kotlinx.html.dom.*
import kotlin.browser.window


fun main() {
    val myDiv = document.create.div {
        (1 until 2).forEach {
            p {
                +"hello there $it"
            }
        }

    }

    window.document.body?.appendChild(myDiv)

    window.setTimeout({
        myDiv.replaceWith(document.create.div {
            (1 until 100).forEach {
                p {
                    +"hello there $it"
                }
            }

        })
    },5000)

}