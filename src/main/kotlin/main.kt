package main

import kotlin.browser.document

import kotlinx.html.*
import kotlinx.html.dom.*
import kotlin.browser.window


fun main() {
    val myDiv = document.create.div {
        h1 {
            +"I can has DOM access"
        }

        (1 until 5).forEach {
            p {
                +"hello there $it"
            }
        }
        p {
            +"wait for it"
        }

    }

    window.document.body?.appendChild(myDiv)

    window.setTimeout({
        myDiv.replaceWith(document.create.div {
            (1 until 1000).forEach {
                p {
                    +"Oh Hai! $it"
                }
            }

        })
    },5000)

}